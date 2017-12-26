$("#login").click(function () {
    user = {
        phone : $("#phone").val(),
        password : $("#password").val()
    };
    $.ajax({
        url: "/login" ,
        type: "POST",
        data:JSON.stringify(user),
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            var jwt = data.jwt;
            localStorage.setItem("jwt", jwt);
            localStorage.setItem("userId",data.id);
            if(data.type == "student"){
                window.location.href = "/student";
            }
            else{
                window.location.href = "/teacher";
            }
        }
    });
});
$("#register").click(function(){
    window.location.href = "/register";
});