$(function () {
    $.ajax({
        url: "http://apis.map.qq.com/ws/district/v1/list?key=7DFBZ-K4PWQ-TYK5Y-GL7XN-RBDSQ-XSB6M&output=jsonp",
        type: "GET",
        dataType: "JSONP",
        success: function (data) {
            var result = data.result[0];
            for(var i = 0;i<result.length;i++)
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
                    console.log($("#city option:selected").attr("value"));
                    $.ajax({
                        url: "/school?city=" + $("#city option:selected").attr("value"),
                        type: "GET",
                        success: function (data) {
                            for (var i = 0; i < data.length; i++)
                                $("#school").append(
                                    "<option value=\"" + data[i].name + "\" id=\"" + data[i].id + "\">" + data[i].name + "</option>"
                                );
                        }
                    })
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
                for(var i = 0;i<result.length;i++)
                    $("#city").append(
                        "<option value=\"" + result[i].name + "\" id=\"" + result[i].id + "\">" + result[i].name + "</option>"
                    );
                $.ajax({
                    url: "/school?city=" + $("#city option:selected").attr("value"),
                    type: "GET",
                    success: function (data) {
                        for (i = 0; i < data.length; i++) {
                            $("#school").append(
                                "<option value=\"" + data[i].name + "\" id=\"" + data[i].id + "\">" + data[i].name + "</option>"
                            );
                        }
                    }
                })
            }
        });
    });

    $("#city").change(function () {
        $("#school").empty();
        $.ajax({
            url: "/school?city=" + $("#city option:selected").attr("value"),
            type: "GET",
            success: function (data) {
                for (i = 0; i < data.length; i++) {
                    $("#school").append(
                        "<option value=\"" + data[i].name + "\" id=\"" + data[i].id + "\">" + data[i].name + "</option>"
                    );
                }
            }
        });
    });

    $("#submit").on("click", function() {
        studentInfo = {
            name: $("#name").val(),
            phone: $("#phoneNum").val(),
            password: $("#password").val(),
            gender: $("input[name=gender]:checked").val() == "男" ? 0 : 1,
            number: $("#stuffNum").val(),
            email: $("#eMail").val(),
            school: {
                id: $("#school option:selected").attr("id")
            }
        };
        $.ajax({
            url: "/me",
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(studentInfo),
            success: function () {
                alert("绑定信息成功!");
                window.location.href = "/student/home";
            }
        });
    });
})