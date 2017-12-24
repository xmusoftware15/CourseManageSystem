function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg); //获取url中"?"符后的字符串并正则匹配
    var context = "";
    if (r != null)
        context = r[2];
    reg = null;
    r = null;
    return context == null || context == "" || context == "undefined" ? "" : context;
}

var topicId = getQueryString("topicId") != "" ? getQueryString("topicId") : 1;
var courseId = getQueryString("courseId") != "" ? getQueryString("courseId") : 23;
var isFixedGroup = getQueryString("isFixedGroup") != "" ? getQueryString("isFixedGroup") : 1;

$(function () {
    if (isFixedGroup == 0) $(".modifyButton").hide();

    if(localStorage.hasOwnProperty("courseName") && localStorage.hasOwnProperty("courseDescription")) {
        $(".courseName").text(localStorage.getItem("courseName"));
        $(".courseIntroduction").text(localStorage.getItem("courseDescription"));
    } else {
        $.ajax({
            url: "/course/" + courseId,
            //url: "http://rap2api.taobao.org/app/mock/933/GET/course/" + courseId,
            type: "GET",
            contentType: "application/json",
            success: function (data) {
                console.log(data);
                $(".courseName").text(data.name);
                $(".courseIntroduction").text(data.description);
                localStorage.setItem("courseName", data.name);
                localStorage.setItem("courseDescription", data.description);
            }
        });
    }

    //获得topic的具体信息
    $.ajax({
        url: "/topic/" + topicId,
        // url: "http://rap2api.taobao.org/app/mock/933/GET/topic/257",
        type: "GET",
        success: function (data) {
            console.log(data);
            $("#topicName").text(data.name);
            $("#topicDesc").text(data.description);
            $("#groupLimit").text(data.groupNumberLimit);
            $("#groupLeft").text(data.groupLeft);
        }
    });

    //回到上一页
    $(".returnButton").click(function () {
        window.location.href = "/student/discussionClass";
    });


    $(".body").on("click", ".modifyButton", function () {
        //id存储剩余组数

        if ($("#groupLeft").text > "0") {
            if (confirm("确认选择该话题")) {
                //todo
                //获取小组id
                //GET /seminar/{seminarId}/group?include={studentId}
                //假装已经获得
                var groupId = 28;
                var topicId = $(this).parent().attr("id");
                var topic = {
                    id: topicId
                };

                $.ajax({
                    url: "/group/" + groupId + "/topic",
                    // url: "http://rap2api.taobao.org/app/mock/933/POST/group/28/topic",
                    type: "POST",
                    contentType: "application/json",
                    data: JSON.stringify(topic),
                    success: function (data) {
                        console.log(data);
                        window.location.href = "/student/discussionClass";
                    }
                });
            }
        } else {
            alert("该话题已经被选满")
        }
    });
});