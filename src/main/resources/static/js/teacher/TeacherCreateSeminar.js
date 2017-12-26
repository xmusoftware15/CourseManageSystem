$(function () {
    var token = localStorage.getItem("jwt");
    var courseId = localStorage.getItem("courseId");
    var courseJson = localStorage.getItem("courseJson");
    courseJson = JSON.parse(courseJson);
    $(".courseName").text(courseJson.name);
    $(".courseIntroduction").text(courseJson.description);


    //返回上一页
    $(".returnButton").click(function () {
        window.location.href = "/teacher/courseInformation";
    })

    //点击了提交按钮
    $(".submit").click(function () {
        var model = $("select[class=bigSelect]").find("option:selected").text();
        if (model == "固定分组") {
            var pmodel = "fixed";
        } else {
            var pmodel = "random";
        }
        var pproportions = {
            report: $("input[name=reportGrade]").val(),
            presentation: $("input[name=presentationGrade]").val(),
            c: $("input[name=c]").val(),
            b: $("input[name=b]").val(),
            a: $("input[name=a]").val(),
        }
        var seminarInfo = {
            name: $("input[name=seminarName]").val(),
            description: $("#desc").val(),
            groupingMethod: pmodel,
            startTime: $("input[name=startTime]").val(),
            endTime: $("input[name=endTime]").val(),
            proportions: pproportions,
        }
        console.log(seminarInfo);

        $.ajax({
            url: "/course/" + courseId + "/seminar",
            type: "POST",
            headers: {"Authorization": "Bearer " + token},
            contentType: "application/json",
            data: JSON.stringify(seminarInfo),
            success: function (data) {
                alert("创建讨论课成功!");
                window.location.href = "/teacher/courseInformation";
            },
            error:function () {
                console.log("出现了错误");
            }
        })
    })

    //点击了重置按钮
    $(".reset").click(function () {
        $("input[name=seminarName]").val("");
        $("#desc").val("");
        $("input[name=startTime]").val("");
        $("input[name=endTime]").val("");
        $("input[name=reportGrade]").val("");
        $("input[name=presentationGrade]").val("");
        $("input[name=c]").val("");
        $("input[name=b]").val("");
        $("input[name=a]").val("");
    })
})