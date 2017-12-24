function getInfo() {
    var classJson = JSON.parse(localStorage.getItem("classJson"));
    // console.log(classJson);
    $("#className").val(classJson.name);
    $("#classSite").val(classJson.site);
    $("#seminarGrade").val(classJson.proportions.presentation);
    $("#reportGrade").val(classJson.proportions.report);
    $("#a").val(classJson.proportions.a);
    $("#b").val(classJson.proportions.b);
    $("#c").val(classJson.proportions.c);
}

$(function () {
    var courseJson = localStorage.getItem("courseJson");
    if (courseJson != null) {
        courseJson = JSON.parse(courseJson);
        $(".courseName").text(courseJson.name);
        $(".courseIntroduction").text(courseJson.description);
    }

    getInfo();

    var num = 1;
    $("#submit").on("click", function () {
        console.log($("#week1 option:selected").text())
        var classTime = "";
        for (var i = 0; i < num; i++) {
            if (i == 0)
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
            url: "/class/" + 2,
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(classInfo),
            success: function (data) {
                alert("修改成功!");
                window.location.href = "/teacher/classInfo";
            }
        });
    });

    $("#reset").on("click", function () {
        getInfo();
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
        win
    })
})