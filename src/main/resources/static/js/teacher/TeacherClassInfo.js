$(function () {
    var classId = localStorage.getItem("classId");
    console.log(classId);

    var courseJson = localStorage.getItem("courseJson");
    courseJson = JSON.parse(courseJson);
    $(".courseName").text(courseJson.name);
    $(".courseIntroduction").text(courseJson.description);


    $.ajax({
        url:"/class/"+classId,
        // url: "http://rap.taobao.org/mockjsdata/29816/class/23",
        type: "GET",
        success: function (data) {
            console.log(data);
            $(".title").text(data.name);
            $("#className").text(data.name);
            $("#time").text(data.time);
            $("#site").text(data.site);
            $("#report").text(data.proportions.report);
            $("#presentation").text(data.proportions.presentation);
            $("#c").text(data.proportions.c);
            $("#b").text(data.proportions.b);
            $("#a").text(data.proportions.a);
            localStorage.setItem("classJson", JSON.stringify(data));
        }
    })
    //返回上一页
    $(".returnButton").click(function () {
        window.history.back();
    })

    //跳转修改页面
    $("#modify").click(function () {
        window.location.href = "/teacher/updateClass";
    })

    //删除课程
    $("#delete").click(function () {
        if(confirm("是否确认删除该课程")){
            $.ajax({
                url:"/class/"+classId,
                type:"DELETE",
                success:function () {
                    //删除成功跳转回课程主页
                    window.location.href ="/teacher/courseInformation";
                },
                error:function () {
                    console.log("删除失败");
                }
            })
        }
    })
})