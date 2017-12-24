$(function () {
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

    //var courseId = getQueryString("courseId")!=""?getQueryString("courseId"):23;
    courseId = 23;//测试数据有误
    var classId = getQueryString("classId") != "" ? getQueryString("classId") : 34;
    console.log(courseId);

    //通过localStorage缓存
    if(localStorage.hasOwnProperty("courseJson")) {
        var courseStr = localStorage.getItem("courseJson");
        var courseJson = JSON.parse(courseStr);
        $(".courseName").text(courseJson.name);
        $(".courseIntroduction").text(courseJson.description);
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
                localStorage.setItem("courseJson", JSON.stringify(data));
            }
        });
    }

    $.ajax({
        url:"/course/"+courseId+"/seminar",
        //url: "http://rap.taobao.org/mockjsdata/29816/course/" + courseId + "/seminars",
        type: "GET",
        success: function (data) {
            console.log(data);
            if (data.length != undefined) {
                for (var i = 0; i < data.length; i++) {
                    $("#seminer_content").append("" +
                        "<div class=\"block\" name='seminer' id='" + data[i].id + "' >" +
                        "  <div class=\"blockFont\">" + data[i].name +
                        "  </div>" +
                        "</div>");
                }
            }
        }
    });

    //点击后跳转到讨论课详情
    $(".body").on("click", "[name='seminer']", function () {
        var seminarId = $(this).attr("id");
        console.log(seminarId);
        localStorage.setItem("seminarId", seminarId.toString());
        window.location.href = "/student/discussionClass";
    });

    //跳转到查看固定分组
    $(".body").on("click", "#fix_group", function () {
        window.location.href = "/student/viewGroup";
    });

    $(".returnButton").click(function () {
        window.location.href = "/student/courseHome";
    });
})
