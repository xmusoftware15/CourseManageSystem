$("#returnButton").click(function(){
    window.history.go(-1);
});

$(document).ready(function () {
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);  //获取url中"?"符后的字符串并正则匹配
        console.log(r);
        var context = "";
        if (r != null)
            context = r[2];
        context = decodeURI(context);
        reg = null;
        r = null;
        return context == null || context == "" || context == "undefined" ? "" : context;
    }
    var courseId = GetQueryString("courseId");

    $("#submitButton").click(function(){
        if($("#group").find("option:selected").text() == "随机分组")
            group = "random";
        else
            group = "fixed";
        proportions = null;
        classInfo = {
            name: $("#name").val(),
            description: $("#description").val(),
            groupingMethod : group,
            startTime: $("#startTime").val(),
            endTime: $("#endTime").val(),
            proportions: proportions
        };
        $.ajax({
            url: "/course/" + courseId + "/seminar" ,
            type: "POST",
            headers : {'Authorization':'Bearer '+localStorage.getItem("jwt")},
            data:JSON.stringify(classInfo),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                alert("创建成功!");
                window.location.href = "/teacher/course/home?courseId="+courseId;
            }
        });
    });
});