$(document).ready(function() {
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);  //获取url中"?"符后的字符串并正则匹配
        console.log(r);
        var context = "";
        if (r != null)
            context =r[2];
        //context = decodeURI(context);
        reg = null;
        r = null;
        return context == null || context == "" || context == "undefined" ? "" : context;
    }
    seminarId = GetQueryString("seminarId");
    courseId = GetQueryString("courseId");
    // $("#"+GetQueryString("delete")).remove();
});
var seminarId;
var courseId;
$("#submitButton").click(function(){
    topic = {
        serial: $("#serial").val(),
        name: $("#name").val(),
        description: $("#description").text(),
        groupLimit: $("#groupLimit").val(),
        groupMemberLimit: $("#numLimit").val()
    };
    $.ajax({
        url: "/seminar/"+seminarId+"/topic",
        type: "POST",
        data: JSON.stringify(topic),
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data);
            alert("创建成功");
            window.location.href = "/teacher/course/seminarInfo?seminarId="+seminarId+"&courseId="+courseId;
        }
    });
});
$("#returnButton").click(function(){
   window.location.href = "/teacher/course/seminarInfo?seminarId="+seminarId+"&courseId="+courseId;
});