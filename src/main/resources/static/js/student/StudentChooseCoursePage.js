$(function () {
    var Url = "/class";
    // var Url = "http://rap.taobao.org/mockjsdata/29816/get/class";
    $.get(Url, function (data) {
        console.log(data);
        if (data.length != undefined) {
            for (var i = 0; i < data.length; i++) {
                $(".content").append(
                    "<div class=\"main_box_right_content\" id=" + data[i].id + ">" +
                    "  <h3 class=\"classtitle\">" + data[i].courseName + "" +
                    "    <button id='selectClass'>选择课程</button>" +
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
    //点击选课按钮后的情况
    $(".body").on("click", "#selectClass", function () {
        if (confirm("确认选择该班级")) {
            var classId = $(this).parent().parent().attr("id");
            var student = {
                id: localstorage.getItem("userId")
            }
            console.log(classId);
            $.ajax({
                //url: "http://rap2api.taobao.org/app/mock/933/POST/class/34/student",
                 url: "/class/"+classId+"/student",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(student),
                success: function (data) {
                    console.log(data);
                    alert("选课成功");
                    $("#selectClass").hide();
                }
            });
        }
    })
})