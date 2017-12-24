$(function () {
    // console.log("aaa");
    $.ajax({
        url: "/me",
        // url: "http://rap2api.taobao.org/app/mock/933/GET/me",
        type: "GET",
        success: function (data) {
            console.log(data);
            $("#userTitle").text(data.phone);
            $("#number").text(data.number);
            $("#name").text(data.name);
            if (data.gender == "male") {
                $("#gender").text("男");
            }
            else {
                $("#gender").text("女");
            }
            $("#school").text(data.school.name);
            $("#title").text(data.title);
            $("#email").text(data.email);
            $("#phone").text(data.phone);

            localStorage.setItem("userJson", JSON.stringify(data));
        }
    })

    //修改按钮
    $(".button").click(function () {
        window.location.href = "/teacher/infoModify";
    })
})