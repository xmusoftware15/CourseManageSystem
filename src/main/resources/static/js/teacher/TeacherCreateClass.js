(function () {

    var num = 1;

    if(localStorage.hasOwnProperty("courseJson")) {
        var courseJson = localStorage.getItem("courseJson");
        var course = JSON.parse(courseJson);
        $(".courseName").text(course.name);
        $(".courseIntroduction").text(course.description);
    } else {
        $.ajax({
            url: "/course/" + 3/*localStorage.getItem("courseId")*/,
            type: "GET",
            success: function (data) {
                $(".courseName").text(data.name);
                $(".courseIntroduction").text(data.description);
                localStorage.setItem("courseJson", JSON.stringify(data));
            }
        });
    }

    $(".submit").on("click", function () {
        console.log($("#week1 option:selected").text())
        var classTime = "";
        for(var i=0;i<num;i++) {
            if(i == 0)
                classTime += $("#week option:selected").text() + $("#day option:selected").text() + $("#time option:selected").text();
            else
                classTime += ("," + $("#week" + i + " option:selected").text() + $("#day" + i + " option:selected").text() + $("#time" + i + " option:selected").text());
        }
        var classInfo = {
            name: $("#className").val(),
            site: $("#classSite").val(),
            time: classTime,
            proportions: {
                presentation: $("#seminarGrade").val(),
                report: $("#reportGrade").val(),
                c: $("#c").val(),
                b: $("#b").val(),
                a: $("#a").val()
            }
        };

        $.ajax({
            url: "/course/" + 2 + "/class",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(classInfo),
            success: function (data) {
                alert("课程创建成功!");
                window.location.href = "/teacher/courseInformation";
            }
        });
    });

    $("#reset").on("click", function () {
        $("input").val("");
    });

    $(".addButton").on("click", function () {
        $(this).parent().after(
            "<div class=\"item\">\n" +
            "                    <label class=\"itemName\">上课时间:</label>\n" +
            "                    <select class=\"bigSelect\" id=\"week" + num + "\">\n" +
            "                        <option value=\"odd\">单周</option>\n" +
            "                        <option value=\"even\">双周</option>\n" +
            "                        <option value=\"every\">每周</option>\n" +
            "                    </select>\n" +
            "                    <select class=\"smallSelect\" id=\"day" + num + "\">\n" +
            "                        <option value=\"Mon\">周一</option>\n" +
            "                        <option value=\"Tue\">周二</option>\n" +
            "                        <option value=\"Wed\">周三</option>\n" +
            "                        <option value=\"Thur\">周四</option>\n" +
            "                        <option value=\"Fri\">周五</option>\n" +
            "                    </select>\n" +
            "                    <select class=\"smallSelect\" id=\"time" + num + "\">\n" +
            "                        <option value=\"one\">一二节</option>\n" +
            "                        <option value=\"three\">三四节</option>\n" +
            "                        <option value=\"five\">五六节</option>\n" +
            "                        <option value=\"seven\">七八节</option>\n" +
            "                        <option value=\"nine\">九十节</option>\n" +
            "                    </select>\n" +
            "                </div>"
        );
        num++;
    });
    
    $(".returnButton").on("click", function () {
        window.history.back();
    })
})();