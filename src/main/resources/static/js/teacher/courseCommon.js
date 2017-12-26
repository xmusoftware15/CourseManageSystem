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
    getCourseInfo(courseId);
});
function getCourseInfo(courseId) {
    $.ajax({
        url: "/course/" + courseId,
        type: "GET",
        headers: {'Authorization': 'Bearer ' + localStorage.getItem("jwt")},
        success: function (data) {
            console.log(data);
            console.log(data.length);
            $("#courseName").html(data.name);
            $("#courseDescription").html(data.description);
        }
    });
}