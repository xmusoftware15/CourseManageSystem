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
    GetSeminarInfo(seminarId);
    // $("#"+GetQueryString("delete")).remove();
});
var id;
var courseId;
var seminarId;
var info;
function GetSeminarInfo(e){
        $.ajax({
            url: "/seminar/" + e,
            type: "GET",
            success: function (data) {
                id = data.id;
                info = data;
                $("#titleName").html(data.name);
                $("#name").html(data.name);
                $("#description").html(data.description);
                if(data.groupingMethod ==  "fixed"){
                    $("#groupingMethod").html("固定分组");
                }else{
                    $("#groupingMethod").html("随机分组");
                }
                $("#startTime").html(getMyDate(data.startTime));
                $("#endTime").html(getMyDate(data.endTime));
                html = "";
                for (i = 0; i < data.topics.length; i++){
                    html += '<div class="block" onclick="topicInfo(\''+ data.topics[i].id+'\')">\n' +
                        '                    <div class="blockFont">'+ data.topics[i].name+'</div>\n' +
                        '                </div>';
                }
                $("#seminarInfo").prepend(html);
            }
        });
};

function getMyDate(str){
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth()+1,
        oDay = oDate.getDate(),
        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) ;//最后拼接时间
    return oTime;
};
//补0操作
function getzf(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
};

function deleteSeminar(){
    var r = confirm("确认删除?");
    if(r == true) {
        $.ajax({
            url: "/seminar/" + id,
            type: "DELETE",
            success: function (data) {
                alert("删除成功");
                window.history.go(-1);
            }
        });
    }
};

function topicInfo(topicId){
    window.location.href = "/teacher/course/topicInfo?topicId="+topicId+"&courseId="+courseId+"&seminarId="+ seminarId;
}

$("#modifyButton").click(function(){
    $("#nameM").val(info.name);
    $("#descriptionM").val(info.description);
    $("#startTimeM").val(getMyDate(info.startTime));
    $("#endTimeM").val(getMyDate(info.endTime));
});

$("#submitButton").click(function () {
    var group;
    if($("#groupingMethodM").find("option:selected").text() == "固定分组"){
        group = "fixed";
    }else{
        group = "random";
    }
   topic = {
       serial: info.serial,
       name: $("#nameM").val(),
       groupingMethod : group,
       description: $("#descriptionM").val(),
       startTime: $("#startTimeM").val(),
       endTime: $("#endTimeM").val(),
       proportions : null
   };
    $.ajax({
        url: "/seminar/"+seminarId,
        type: "PUT",
        headers : {'Authorization':'Bearer '+localStorage.getItem("jwt")},
        data: JSON.stringify(topic),
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data);
            alert("修改成功");
            window.location.reload();
        }
    });
});
$("#returnButton").click(function () {
    window.location.href = "/teacher/course/home?courseId="+courseId;
});

$("#topicAdd").click(function(){
    window.location.href = "/teacher/course/createTopic?courseId="+courseId+"&seminarId="+ seminarId;
});

$("#grade").click(function () {
    window.location.href = "/teacher/course/grade";
});

$("#viewChoose").click(function(){
   window.location.href = "/teacher/course/viewChoose?seminarId="+seminarId+"&courseId="+courseId;
});

