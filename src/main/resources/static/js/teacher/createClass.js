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

    $("#submitClass").click(function(){
        proportions = {
            report: $("#report").val(),
            presentation: $("#presentation").val(),
            c: $("#c").val(),
            b: $("#b").val(),
            a: $("#a").val()
        };
        classInfo = {
            name: $("#name").val(),
            site: $("#site").val(),
            time: $("select#week").find("option:selected").text()
            + $("select#day").find("option:selected").text()
            + $("select#detail").find("option:selected").text(),
            proportions: proportions
        };
        $.ajax({
            url: "/course/" + courseId + "/class" ,
            type: "POST",
            headers : {'Authorization':'Bearer '+localStorage.getItem("jwt")},
            data:JSON.stringify(classInfo),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                id = data.id;
                alert("创建成功!");
            }
        });
    });

});
var id;
 $("#uploadFile").click(function () {
     var formData = new FormData();
     formData.append("file", $( "#studentFile" )[0].files[0]);
     formData.append("classId",id);
    $.ajax({
        url: '/importStudents' ,
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (returndata) {
            alert("导入成功");
        },
        error: function (returndata) {
            alert("导入失败，请检查文件类型！");
        }
    });


})
$("#returnButton").click(function(){
    window.history.go(-1);
});
