$(function () {
    var courseJson = localStorage.getItem("courseJson");
    var seminarId = localStorage.getItem("seminarId");
    if (courseJson != null) {
        courseJson = JSON.parse(courseJson);
        $(".courseName").text(courseJson.name);
        $(".courseIntroduction").text(courseJson.description);
    }

    //点击了提交按钮
    $(".submit").click(function () {
        var topicInfo = {
            serial:$("input[name=serial]").val(),
            groupLimit: $("input[name=groupLimit]").val(),
            name:$("input[name=seminarName]").val(),
            groupMemberLimit:$("input[name=groupMemberLimit]").val(),
            description:$("#desc").val(),
        }
        console.log(topicInfo);
        $.ajax({
            url:"/seminar/" + seminarId + "/topic",
            type:"POST",
            contentType: "application/json",
            data: JSON.stringify(topicInfo),
            success:function () {
                alert("添加成功");
                window.location.href = "/teacher/seminarInfo";
            },
            error:function () {
                alert("修改失败");
            }
        })
    })

    //点击了重置按钮
    $(".reset").click(function () {
        $("input[name=seminarName]").val("");
        $("#desc").val("");
        $("input[name=groupLimit]").val("");
        $("input[name=groupMemberLimit]").val("");
        $("input[name=serial]").val("");
    })

    //返回上一页
    $(".returnButton").click(function () {
        window.history.back();
    })
})