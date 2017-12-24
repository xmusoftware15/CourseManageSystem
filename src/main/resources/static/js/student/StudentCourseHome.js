var classId = 23;
$(function () {
    var Url = "/class";
    //var Url = "http://rap.taobao.org/mockjsdata/29816/get/class";
    $.get(Url, function (data) {
        console.log(data);
        if (data.length != undefined) {
            for (var i = 0; i < data.length; i++) {
                $(".content").append(
                    "<div class=\"main_box_right_content\" id=" + data[i].id + ">" +
                    "  <h3 class=\"classtitle\">" + data[i].courseName + "" +
                    "    <button class='dropClass'>退选课程</button>" +
                    "  </h3>" +
                    "  <div class=\"divideline\"></div>" +
                    "  <div class=\"classinfo\">" +
                    "  <table class=\"table\">" +
                    "    <tr>" +
                    "      <td class=\"tabletext\">班级：<span>" + data[i].name + "</span></td>" +
                    "      <td class=\"tabletext\">课程地点：<span>" + data[i].site + "</span></td>" +
                    "    </tr>" +
                    "    <tr>" +
                    "      <td class=\"tabletext\">班级人数：<span>" + data[i].numStudent + "</span></td>" +
                    "      <td class=\"tabletext\">时间：<span>" + data[i].time + "</span></td>" +
                    "    </tr>" +
                    "  </table>" +
                    "  </div>" +
                    "</div>");
            }
        }
    })
    //点击退选按钮
    $(".body").on("click",".dropClass",function () {
        if (confirm("确认退选该班级？")) {
            //var classId = $(this).parent().parent().attr("id");
            var classId = $(this).parent().parent().attr("id");
            var studentId = 2792;
            console.log(classId);
            $.ajax({
                url: "/class/" + classId + "/student/" + studentId,
                type: "DELETE",
                success: function () {
                    alert("成功退选课程");
                    $("#"+classId).hide();
                }
            });
        }
    });
    //点击课程框，跳转到学生课程首页
    $(".body").on("click",".classinfo",function () {
        var courseId = $(this).parent().attr("id");
        var classId = $(this).attr("id");
        window.location.href = "/student/courseInformation";
    })
})