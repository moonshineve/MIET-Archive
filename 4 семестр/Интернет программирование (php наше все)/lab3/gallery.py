from flask import render_template, request, redirect, url_for, send_from_directory, make_response
import os
import re
from PIL import Image
import secrets
from main import app 


os.makedirs("uploads", exist_ok=True)
os.makedirs("thumbnails", exist_ok=True)



FORBIDDEN = ['script', 'http', 'SELECT', 'UNION', 'UPDATE', 'exe', 'exec', 'INSERT', 'tmp']

def check_name(name):
    
    if re.search(r'<[^>]+>', name):
        return False, "Название содержит HTML теги"
    
    name_lower = name.lower()
    for word in FORBIDDEN:
        if word.lower() in name_lower:
            return False, f"Название содержит запрещенное слово: {word}"
    
    return True, "OK"


def create_thumbnail(image_path, thumb_path):
    with Image.open(image_path) as img:
        img.thumbnail((200, 200)) # сжимает
        img.save(thumb_path)


@app.route("/gallery")
def gallery():
    user = request.cookies.get("user")
    if not user:
        return redirect(url_for("authorize"))
    
    images = []
    user_folder = f"uploads/{user}"
    thumb_folder = f"thumbnails/{user}"
    
    if os.path.exists(user_folder):
        for file in os.listdir(user_folder):
            images.append({
                'name': file,
                'path': f"/images/{user}/{file}",
                'thumb': f"/thumbnails/{user}/{file}",
                'title': file.split('_', 1)[1] if '_' in file else file
            })
    
    return render_template("gallery.html", user=user, images=images)


@app.route("/upload", methods=["POST"])
def upload():
    user = request.cookies.get("user")
    if not user:
        return redirect(url_for("authorize"))
    
    title = request.form.get("title", "").strip()
    file = request.files.get("image")

    valid, msg = check_name(title)
    if not valid:
        return msg
    
    if not file.filename.lower().endswith(('.png', '.jpg', '.jpeg', '.gif')):
        return "Поддерживаются только PNG, JPG, JPEG, GIF"
    
    file.seek(0, os.SEEK_END)
    size = file.tell()
    file.seek(0)
    
    if size > 2 * 1024 * 1024:
        return "Файлы больше 2MB не поддерживаются"
    
    user_folder = f"uploads/{user}"
    thumb_folder = f"thumbnails/{user}"

    os.makedirs(user_folder, exist_ok=True)
    os.makedirs(thumb_folder, exist_ok=True)
    

    filename = title
    filepath = os.path.join(user_folder, filename)
    file.save(filepath)
    
    
    thumb_path = os.path.join(thumb_folder, filename)
    create_thumbnail(filepath, thumb_path)
    
    return redirect(url_for("gallery"))


@app.route("/delete/<filename>") # Явно не вызывается пользователем
def delete(filename):
    user = request.cookies.get("user")
    
    for folder in ["uploads", "thumbnails"]:
        filepath = f"{folder}/{user}/{filename}"
        if os.path.exists(filepath):
            os.remove(filepath)
    
    return redirect(url_for("gallery"))


@app.route("/images/<user>/<filename>") # Явно не вызывается пользователем
def get_image(user, filename):
    return send_from_directory(f"uploads/{user}", filename)


@app.route("/thumbnails/<user>/<filename>") # Явно не вызывается пользователем
def get_thumbnail(user, filename):
    return send_from_directory(f"thumbnails/{user}", filename)