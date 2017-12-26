(function () {
    $.ajax({
        url: "http://apis.map.qq.com/ws/district/v1/list?key=7DFBZ-K4PWQ-TYK5Y-GL7XN-RBDSQ-XSB6M&output=jsonp",
        type: "GET",
        dataType: "JSONP",
        success: function (data) {
            console.log(data);
            var result = data.result[0];
            for (var i = 0; i < result.length; i++)
                $("#province").append(
                    "<option value=\"" + result[i].name + "\" id=\"" + result[i].id + "\">" + result[i].name + "</option>"
                );
            $.ajax({
                url: "http://apis.map.qq.com/ws/district/v1/getchildren?key=7DFBZ-K4PWQ-TYK5Y-GL7XN-RBDSQ-XSB6M&output=jsonp&id=" + $("#province option:selected").attr("id"),
                type: "GET",
                dataType: "JSONP",
                success: function (data) {
                    var result = data.result[0];
                    for (var i = 0; i < result.length; i++)
                        $("#city").append(
                            "<option value=\"" + result[i].name + "\" id=\"" + result[i].id + "\">" + result[i].name + "</option>"
                        );
                }
            });
        }
    });

    $("#province").change(function () {
        $("#city").empty();
        $.ajax({
            url: "http://apis.map.qq.com/ws/district/v1/getchildren?key=7DFBZ-K4PWQ-TYK5Y-GL7XN-RBDSQ-XSB6M&output=jsonp&id=" + $("#province option:selected").attr("id"),
            type: "GET",
            dataType: "JSONP",
            success: function (data) {
                var result = data.result[0];
                for (var i = 0; i < result.length; i++) {
                    $("#city").append(
                        "<option value=\"" + result[i].name + "\" id=\"" + result[i].id + "\">" + result[i].name + "</option>"
                    );
                    $("#" + result[0].id).attr("selected", "selected");
                }
                readSchool();
            }
        });
    });

    function readSchool(){
        $("#school").empty();
        var city = $("#city option:selected").val();
        console.log(city);
        $.ajax({
            url: "/school",
            type: "GET",
            contentType: "application/json",
            data: {city: city},
            success: function (data) {
                console.log(data);
                var result = data;
                for (var i = 0; i < result.length; i++)
                    $("#school").append(
                        "<option value=\"" + result[i].name + "\" id=\"" + result[i].id + "\">" + result[i].name + "</option>"
                    );
            }
        });
    }

    $("#city").change(function () {
        $("#school").empty();
        var city = $("#city option:selected").attr("value");
        console.log(city);
        $.ajax({
            url: "/school",
            type: "GET",
            contentType: "application/json",
            data: {city: city},
            success: function (data) {
                console.log(data);
                var result = data;
                for (var i = 0; i < result.length; i++)
                    $("#school").append(
                        "<option value=\"" + result[i].name + "\" id=\"" + result[i].id + "\">" + result[i].name + "</option>"
                    );
            }
        });
    });

    $("#createSchool").on("click", function () {
        window.location.href = "/teacher/createSchool";
    });

    $("#submit").on("click", function () {
        var gender;
        var role;
        if($("input[type=radio]:checked")[0].value == "男"){
            gender = "male";
        } else{
            gender = "female";
        }
        info = {
          phone: $("#phoneNum").val(),
          password: $("#password").val(),
          name: $("#name").val(),
            school: $("#school").val(),
            gender : gender,
            number : $("#stuffNum").val(),
            email : $("#email").val()

        };
        $.ajax({
            url: "/register",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(info),
            success: function (data) {
                var jwt = data.jwt;
                localStorage.setItem("jwt", jwt);
                if(data.type == "student"){
                    window.location.href = "/student";
                }
                else{
                    window.location.href = "/teacher";
                }
            }
        });

    });


})();