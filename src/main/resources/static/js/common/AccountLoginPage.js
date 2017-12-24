(function () {
    $("#submit").click(function () {
        var data = {
            phone: $("#username").val(),
            password: $("#password").val()
        };
        $.ajax({
            url: "/login",
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json; charset=UTF-8",
            success: function (data) {
                console(data);
            }
        })
    })
})();
