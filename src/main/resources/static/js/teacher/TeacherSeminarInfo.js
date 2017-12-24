$(function () {
    var seminarId = localStorage.getItem("seminarId");
    console.log(seminarId);

    var courseJson = localStorage.getItem("courseJson");
    if (courseJson != undefined) {
        courseJson = JSON.parse(courseJson);
        $(".courseName").text(courseJson.name);
        $(".courseIntroduction").text(courseJson.description);
    }

    $.ajax({
        url:"/seminar/"+seminarId,
        // url: "http://rap2api.taobao.org/app/mock/933/GET/seminar/32",
        type: "GET",
        success: function (data) {
            console.log(data);
            //存储json
            var json = JSON.stringify(data);
            localStorage.setItem("seminarJson", json);

            $("#seminarTitle").text(data.name);
            $("#name").text(data.nema);
            $("#desc").text(data.description);
            $("#groupModel").text(data.groupingMethod == "fixed" ? "固定分组" : "随机分组");
            $("#startTime").text(data.startTime);
            $("#endTime").text(data.endTime);

            //填写话题部分
            if (data.topics.length != undefined) {
                for (var i = 0; i < data.topics.length; i++) {
                    $("#topicContent").append("  <div class=\"topicBlock\" id=" + data.topics[i].id + " name='topic'>\n" +
                        "                        <div class=\"topicBlockFont\">" + data.topics[i].name + "</div>\n" +
                        "                    </div>")
                }
            }
            //添加加号
            $("#topicContent").append("<div class=\"topicBlock\" id='addTopic'>\n" +
                "                        <img class=\"addImg\" src=\"/img/smalladd.png\" alt=\"添加\">\n" +
                "                    </div>")
        }
    })
    //进入评分页面
    $(".body").on("click", "#grade", function () {
        window.location.href = "/teacher/scoreHome";
    })
    //进入修改seminar页面
    $(".body").on("click", "#modily", function () {
        window.location.href = "/teacher/updateSeminar";
    })
    //删除seminar
    $(".body").on("click", "#delete", function () {
        if (confirm("确认删除该讨论课吗？")) {
            console.log(seminarId);
            $.ajax({
                url: "/seminar/" + seminarId,
                type: "DELETE",
                success: function () {
                    alert("成功删除课程");
                    window.location.href = "/teacher/courseInformation";
                },
                error: function () {
                    alert("删除课程失败");
                }
            })
        }
    })

    //进入添加topic界面
    $(".body").on("click","#addTopic",function () {
        window.location.href = "/teacher/createTopic";
    })


    //进入topic详细信息
    $(".body").on("click", "div[name=topic]", function () {
        var topicId = $(this).attr("id");
        //console.log(topicId);
        localStorage.setItem("topicId", topicId);
        window.location.href = "/teacher/topicCheck";
    })


    //返回上一页
    $(".returnButton").click(function () {
        window.history.back();
    })

})