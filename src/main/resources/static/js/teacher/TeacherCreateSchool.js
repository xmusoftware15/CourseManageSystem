(function () {
    $.ajax({
        url: "http://apis.map.qq.com/ws/district/v1/list?key=7DFBZ-K4PWQ-TYK5Y-GL7XN-RBDSQ-XSB6M&output=jsonp",
        type: "GET",
        dataType: "JSONP",
        success: function (data) {
            console.log(data);
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
                    for(var i = 0;i<result.length;i++)
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
                for(var i = 0;i<result.length;i++)
                    $("#city").append(
                        "<option value=\"" + result[i].name + "\" id=\"" + result[i].id + "\">" + result[i].name + "</option>"
                    );
            }
        });
    })
    
    $("#submit").on("click", function () {
        school = {
            name: $("#school").val(),
            province: $("#province option:selected").attr("value"),
            city: $("#city option:selected").attr("value")
        };
        $.ajax({
            url: "/school",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(school),
            success: function (data) {
                alert("success");
            }
        })
    });
})();