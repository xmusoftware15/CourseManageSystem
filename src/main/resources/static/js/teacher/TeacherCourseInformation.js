$(function () {
    var courseId = localStorage.getItem("courseId");
    console.log(courseId);
    var token = localStorage.getItem("jwt");

    $.ajax({
        url:"/course/"+courseId,
        // url: "http://rap2api.taobao.org/app/mock/933/GET/course/23",
        type: "GET",
        headers: {"Authorization": "Bearer " + token},
        success: function (data) {
            console.log(data);
            //json存储
            var json = JSON.stringify(data);
            localStorage.setItem("courseJson", json)

            var courseJson = localStorage.getItem("courseJson");
            courseJson = JSON.parse(courseJson);
            $(".courseName").text(courseJson.name);
            $(".courseIntroduction").text(courseJson.description);
        }

    })
    //班级展示
    $.ajax({
        url:"/course/"+courseId+"/class",
        // url: "http://rap.taobao.org/mockjsdata/29816/course/23/calss",
        type: "GET",
        headers: {"Authorization": "Bearer " + token},
        success: function (data) {
            console.log(data);
            if (data.length != undefined) {
                for (var i = 0; i < data.length; i++) {
                    $("#classContent").append("<div class=\"block\" id=" + data[i].id + " name='class'>\n" +
                        "                    <div class=\"blockFont\">" + data[i].name + "</div>\n" +
                        "                </div>")
                }
            }
            $("#classContent").append("              <div class=\"block\" id='addClass'>\n" +
                "                    <img class=\"addImg\" src=\"/img/smalladd.png\" alt=\"添加\">\n" +
                "                </div>")
        }
    })
    //讨论课展示
    $.ajax({
        url:"/course/"+courseId+"/seminar",
        // url: "http://rap.taobao.org/mockjsdata/29816/course/23/seminar",
        type: "GET",
        headers: {"Authorization": "Bearer " + token},
        success: function (data) {
            console.log(data);
            if (data.length != undefined) {
                for (var i = 0; i < data.length; i++) {
                    $("#seminarContent").append("\n" +
                        "                <div class=\"block\" id=" + data[i].id + " name='seminar'>\n" +
                        "                    <div class=\"blockFont\">" + data[i].name + "</div>\n" +
                        "                </div>")
                }
            }
            $("#seminarContent").append(" <div class=\"block\" id='addSeminar'>\n" +
                "                    <img class=\"addImg\" src=\"/img/smalladd.png\" alt=\"添加\">\n" +
                "                </div>")
        }
    })

    //跳转到添加班级
    $(".body").on("click", "#addClass", function () {
        window.location.href = "/teacher/createClass";
    })

    //跳转到添加讨论课
    $(".body").on("click", "#addSeminar", function () {
        window.location.href = "/teacher/createSeminar";
    })

    //返回上一页
    $(".returnButton").click(function () {
        window.history.back();
    })

    //跳转到班级信息
    $(".body").on("click", "div[name=class]", function () {
        var classId = $(this).attr("id");
        // console.log(classId);
        localStorage.setItem("classId", classId);
        window.location.href = "/teacher/classInfo";
    })

    //跳转到讨论课信息
    $(".body").on("click", "div[name=seminar]", function () {
        var seminarId = $(this).attr("id");
        localStorage.setItem("seminarId", seminarId);
        window.location.href = "/teacher/seminarInfo";
    })

})