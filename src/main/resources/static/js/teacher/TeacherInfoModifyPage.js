$(function () {

    var userJson = localStorage.getItem("userJson");
    userJson = JSON.parse(userJson);
    console.log(userJson);

    $("#userName").text(userJson.phone);
    $("input[name=idnum]").val(userJson.number);
    $("input[name=name]").val(userJson.name);
    $("input[name=sex]").val(userJson.gender=="male"?"男":"女");
    $("input[name=school]").val(userJson.school.name);
    $("input[name=title]").val(userJson.title);
    $("input[name=e-mail]").val(userJson.email);
    $("input[name=phone]").val(userJson.phone);

    $("input[name=submit]").click(function () {
        var info = {
            name: $("input[name=name]").val(),
            gender:$("input[name=sex]").val()=="男"?"male":"female",
            title:$("input[name=title]").val(),
            email: $("input[name=e-mail]").val(),
            number:$("input[name=idnum]").val(),
            avatar: "/avatar/3486.png"
        }

        $.ajax({
            url: "/me",
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(info),
            success: function () {
                alert("修改个人信息成功");
                window.location.href = "/teacher/home";
            },
            error:function () {
                alert("修改个人信息失败");
            }
        });
    })
})