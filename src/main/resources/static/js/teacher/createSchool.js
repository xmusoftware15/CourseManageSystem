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
                for (var i = 0; i < result.length; i++)
                    $("#city").append(
                        "<option value=\"" + result[i].name + "\" id=\"" + result[i].id + "\">" + result[i].name + "</option>"
                    );
            }
        });
    });

    $("#createSchool").on("click", function () {
        window.location.href = "/teacher/createSchool";
    });

    function checkQuote(str) {
        console.log(str);
        var items = new Array("~", "`", "!", "@", "#", "$", "%", "^", "&", "*", "{", "}", "[", "]", "(", ")");
        items.push(":", ";", "'", "|", "\\\\", "<", ">", "?", "/", "<<", ">>", "||", "//", ",", "，", ".", "。");
        for (var i = 0; i < items.length; i++) {
            if (str.indexOf(items[i]) >= 0) {
                return true;
            }
        }
        return false;
    }

    $("#submit").on("click", function () {
        if (checkQuote($("#school").val())) {
            alert("学校名不合法，不能包含符号！");
        }
        else {
            var name = $("#school").val().replace(/\s+/g, "");
            var province = $("#province option:selected").attr("value");
            var city = $("#city option:selected").attr("value");
            school = {
                name: $("#school").val().replace(/\s+/g, ""),
                province: $("#province option:selected").attr("value"),
                city: $("#city option:selected").attr("value")
            };

            $.ajax({
                url: "/school",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(school),
                success: function (data) {
                    if(data.id == null){
                        alert("学校已被创建，请重现创建学校");
                    }
                    else{
                        alert("创建成功");
                        window.location.href= "/register";
                    }
                }
            });
        }

    });
})();

