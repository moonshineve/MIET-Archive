$(function () {

    $.validator.addMethod("checkFio", function (value) {
        const isRu = /[а-яё]/i.test(value);
        const isEn = /[a-z]/i.test(value);
        const threeWords = /^\S+\s\S+\s\S+$/.test(value);

        return (isRu || isEn) && threeWords;
    }, "Некорректное ФИО");


    $.validator.addMethod("firstOrThird", function () {
        return $("#gosuslugi").is(":checked") || $("#max").is(":checked");
    }, "Надо выбрать 1 или 3 чекбокс");


    $("#my-form").validate({

        errorLabelContainer: "#error",
        wrapper: "div",

        rules: {
            fio: {
                required: true,
                checkFio: true
            },
            bread: {
                required: true
            },
            gosuslugi: {
                firstOrThird: true
            }
        },

        messages: {
            fio: {
                required: "Введите ФИО"
            },
            bread: {
                required: "Ты должен выбрать, какой ты хлеб"
            }
        },

    });


    $("#fio").on("input", function () {
        localStorage.setItem("fio", $(this).val());
    });

    $("#bread").on("change", function () {
        localStorage.setItem("bread", $(this).val());
    });

    $("#gosuslugi, #green_card, #max").on("change", function () {
        localStorage.setItem("gosuslugi", $("#gosuslugi").is(":checked"));
        localStorage.setItem("green_card", $("#green_card").is(":checked"));
        localStorage.setItem("max", $("#max").is(":checked"));
    });


    if (localStorage.getItem("fio"))
        $("#fio").val(localStorage.getItem("fio"));

    if (localStorage.getItem("bread"))
        $("#bread").val(localStorage.getItem("bread"));

    if (localStorage.getItem("gosuslugi") === "true")
        $("#gosuslugi").prop("checked", true);

    if (localStorage.getItem("green_card") === "true")
        $("#green_card").prop("checked", true);

    if (localStorage.getItem("max") === "true")
        $("#max").prop("checked", true);

});



function checkWidth() {
    const leftColumn = document.getElementById("item1");
    const rightColumn = document.getElementById("item3");

    if (window.innerWidth < 480) {
        leftColumn.style.display = "none";
        rightColumn.style.display = "none";
    } else {
        leftColumn.style.display = "block";
        rightColumn.style.display = "block";
    }
}

window.addEventListener("resize", checkWidth);
window.addEventListener("load", checkWidth);