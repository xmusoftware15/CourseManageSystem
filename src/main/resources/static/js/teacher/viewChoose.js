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
    console.log(GetQueryString("seminarId"));
    seminarId = GetQueryString("seminarId");
    courseId = GetQueryString("courseId");
    GetChooseInfo(seminarId);
    // $("#"+GetQueryString("delete")).remove();
});
var courseId;
var seminarId;
function GetChooseInfo(){
    $.ajax({
        url: "/seminar/" + seminarId + "/group",
        type: "GET",
        success: function (data) {
            
        }
    });
}