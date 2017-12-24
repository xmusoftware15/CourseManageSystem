(function () {
    $("#submit").on("click", function () {
        var course = {
            name: $("#coursename").val(),
            description: $("#courseinfo").val(),
            startTime: $("#begintime").val(),
            endTime: $("#endtime").val(),
            proportions: {
                report: $("#reportscore").val(),
                presentation: $("#seminarscore").val(),
                c: $("#three").val(),
                b: $("#four").val(),
                a: $("#five").val()
            }
        };
        $.ajax({
            url: "/course",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(course),
            success: function (data) {
                alert("课程添加成功！");
                window.location.href = "/teacher/courseHome";
            }
        });
    });

    $("#reset").on("click", function () {
        $("input, textarea").val("");
    })
})();