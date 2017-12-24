(function () {

    if(localStorage.hasOwnProperty("userJson")) {
        var userStr = localStorage.getItem("userJson");
        var userJson = JSON.parse(userStr);
        $("#username").text(userJson.phone);
        $("#number").val(userJson.number);
        $("#name").val(userJson.name);
        $("#gender").val(userJson.gender == "male" ? "男" : "女");
        $("#school").val(userJson.school.name);
        $("#title").val(userJson.title);
        $("#email").val(userJson.email);
        $("#phone").val(userJson.phone);
    } else {
        $.ajax({
            url: "/me",
            type: "GET",
            success: function (data) {
                $("#username").text(data.phone);
                $("#number").val(data.number);
                $("#name").val(data.name);
                $("#gender").val(data.gender == "male" ? "男" : "女");
                $("#school").val(data.school.name);
                $("#title").val(data.title);
                $("#email").val(data.email);
                $("#phone").val(data.phone);
            }
        });
    }

    $('.button button').on('click', function () {
        var data = {
            phone: $("#phone").val(),
            email: $("#email").val(),
            number: $("#number").val(),
            school: {
                name: $("#school").val()
            },
            name: $("#name").val(),
            gender: $("#gender").val() == "男" ? "male" : "female",
            title: $("#title").val()
        };

        $.ajax({
            url: "/me",
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function () {
                alert("修改成功!");
                window.location.href = "/student/home";
            }
        });
    });
})();

