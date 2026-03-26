from flask import Flask, render_template, request, redirect, url_for, make_response

app = Flask(__name__)

# @app.route("/")
# def index():
#     user = request.cookies.get("user")
#     if user:
#         return redirect(url_for("gallery"))
#     return render_template("index.html")

@app.route("/register", methods=["GET", "POST"])
def register():
    if request.method == "POST":
        login = request.form.get("login").strip()
        password = request.form.get("password").strip()

        with open("users_database.txt", "a+") as database:
            database.seek(0)
            for string in database.readlines():
                db_login = string.split(" ")[0]
                if login == db_login:
                    return "Пользователь с таким логином уже существует"
            
            database.write(f"{login} {password}\n")
            return render_template("authorize_form.html")
        
    return render_template("register_form.html")

@app.route("/", methods=["GET", "POST"])
def authorize():
    if request.method == "POST":
        login = request.form.get("login").strip()
        password = request.form.get("password").strip()

        with open("users_database.txt", "r") as database:
            for string in database.readlines():
                db_login, db_password = string.strip("\n").split(" ")
                if login == db_login:
                    if password == db_password:
                        resp = make_response(redirect(url_for("gallery")))
                        resp.set_cookie("user", login, max_age=3600)
                        return resp
                    else:
                        return "Ошибка в пароле, попробуйте снова"
            return "Такой пользователь не найден"
        
    return render_template("authorize_form.html")

from gallery import *

if __name__ == "__main__":
    app.run(debug=True)