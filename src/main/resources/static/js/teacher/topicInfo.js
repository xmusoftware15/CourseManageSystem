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
    courseId = GetQueryString("courseId");
    seminarId = GetQueryString("seminarId");
    topicId = GetQueryString("topicId");
    GetTopicInfo(topicId);
    GetGroup(topicId);
});
var courseId ;
var seminarId;
var topicId;
var info;
function deleteTopics(){
    var r = confirm("确认删除话题？");
    if( r == true){
        $.ajax({
            url: "/topic/"+topicId,
            type: "DELETE",
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                alert("删除成功！");
                window.location.href = "/teacher/course/seminarInfo?seminarId="+seminarId+"&courseId="+courseId;
            }
        });
    }
};
function GetTopicInfo(topicId){
    $.ajax({
        url: "/topic/"+topicId,
        type: "GET",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            info = data;
            console.log(data.description);
            $("#name").html(data.name);
            $("#description").html(data.description);
            $("#groupLimit").html(data.groupLimit);
            $("#memberLimit").html(data.groupMemberLimit);
        }
    });
};
function GetGroup(topicId){
    $.ajax({
        url: "/topic/"+topicId+"/group",
        type: "GET",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            var str = "";
            for(i=0;i<data.length;i++){
                str += data[i].name + "";
            }
            $("#group").html(str);
            $("#groupM").html(str);
        }
    });
}
$("#returnButton").click(function () {
   window.history.go(-1);
});
$("#modifyButton").click(function(){
    console.log("修改！");
    $("#nameM").val(info.name);
    $("#descriptionM").val(info.description);
    $("#groupLimitM").val(info.groupLimit);
    $("#memberLimitM").val(info.groupMemberLimit);
});
$("#submitButton").click(function(){
    topic = {
        serial: info.serial,
        name: $("#nameM").val(),
        decription: $("#descriptionM").val(),
        groupLimit:$("#groupLimitM").val(),
        groupMemberLimit: $("#memberLimitM").val()
    };
    $.ajax({
        url: "/topic/"+topicId,
        type: "PUT",
        data: JSON.stringify(topic),
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            alert("修改成功！");
            window.location.reload();
        }
    });

});