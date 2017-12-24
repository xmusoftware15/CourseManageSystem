(function () {
    $("#submit").on("click", function () {
        var data = {
            username: $("#username").val(),
            password: $("#password").val()
        };
        $.ajax({
            url: "/"
        })
    })
})();