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

var seminarId = localStorage.hasOwnProperty("seminarId") ? localStorage.getItem("seminarId") : 32;
var courseId = getQueryString("courseId") != "" ? getQueryString("courseId") : 23;
var isFixedGroup = 1;

$(function () {
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

    //获取seminar具体数据
    $.ajax({
        url: "/seminar/" + seminarId,
        //url: "http://rap2api.taobao.org/app/mock/933/GET/seminar/" + seminarId,
        type: "GET",
        success: function (data) {
            console.log(data);
            $("#seminarTitle").text(data.name)
            $("#seminarDesc").text(data.description);
            if (data.groupingMethod == "fixed") {
                $("#chooseGroup").text("固定分组");
            } else {
                $("#chooseGroup").text("随机分组");
                isFixedGroup = 0;
                $("#groupInfo").show();
                getGroup();
            }
            $("#startTime").text(data.startTime);
            $("#endTime").text(data.endTime);
        }
    });

    //获取topic数据
    $.ajax({
        url: "/seminar/" + seminarId + "/topic",
        //url: "http://rap.taobao.org/mockjsdata/29816/seminar/" + seminarId + "/topic",
        type: "GET",
        success: function (data) {
            console.log(data);
            if (data.length != undefined) {
                //根据讨论课的数量显示ABC
                var saveLet = ["A", "B", "C", "D", "E", "F", "G", "H"];
                for (var i = 0; i < data.length; i++) {
                    $(".blockBody").append("  " +
                        "<div class=\"smallblock\" id=" + data[i].id + ">\n" +
                        "     <div class=\"blockFont\">话题" + saveLet[i] + "</div>\n" +
                        "</div>")
                }
            }
        }
    });

    function getGroup() {
        //获得小组ID
        $.ajax({
            url:"/seminar/"+seminarId+"/group/my",
            //url: "http://rap2api.taobao.org/app/mock/933/GET/seminar/32/group",
            type: "GET",
            success: function (data) {
                console.log(data);
                if (data.length == undefined) {
                    var groupId = data.id; //28
                    //查询小组的详细信息
                    //http://rap2api.taobao.org/app/mock/933/GET/group/28
                    $.ajax({
                        url:"/group/"+groupId,
                        // url: "http://rap2api.taobao.org/app/mock/933/GET/group/28",
                        type: "GET",
                        success: function (data) {
                            console.log(data);
                            $("#leader").text(data.leader.name);
                            if (data.members.length != undefined) {
                                for (var i = 0; i < data.members.length; i++) {
                                    $("#groupInfo").append("<label class=\"itemName\">" + data.members[i].name + " </label>");
                                }
                            }
                        }
                    });
                }
            }
        })
    }

    //点击进入topic详情
    $(".body").on("click", ".blockFont", function () {
        var topicId = $(this).parent().attr("id");
        //console.log(topicId);
        window.location.href = "/student/viewTopic";
    });

    //点击后上传文件
    $(".uploadbutton").click(function () {
        //todo
        // 需要弹框
    });

    //点击后查看到分数查看界面
    $(".viewscorebutton").click(function () {
        //跳转到分数查看界面
        window.location.href = "/student/viewGrade";
    });

    //回到上一页
    $(".returnButton").click(function () {
        window.location.href = "/student/courseInformation";
    })
})