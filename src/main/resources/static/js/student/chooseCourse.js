function selectCourses(e) {
    var r = confirm("确认选课吗？");
    if (r == true) {
        var classId = $(e).data("id");
        var userId = localStorage.getItem("userId");
        var data = {id: userId};
        $.ajax({
            url: "/class/" + classId + "/student",
            type: "POST",
            data: JSON.stringify(data),
            headers: {'Authorization': 'Bearer ' + localStorage.getItem("jwt")},
            contentType: "application/json",
            success: function (data) {
                alert("选课成功");
                $(e).remove();
            },
            error: function () {
                alert("选课失败");
            }
        });

        // $("#" + e).remove();
    }
};

function searchCourse() {
    var courseName = $("#courseName").val() == "" ? null : $("#courseName").val();
    var teacherName = $("#teacherName").val() ? null : $("#teacherName").val();
    if (courseName == null && teacherName == null) {
        alert("请输入课程或教师姓名");
    }

    $.ajax({
        url: "/class",
        type: "GET",
        data: {courseName: courseName, teacherName: teacherName},
        headers: {'Authorization': 'Bearer ' + localStorage.getItem("jwt")},
        contentType: "application/x-www-form-urlencoded",
        success: function (data) {
            clear();
            for (i = 0; i < data.length; i++) {
                append(data[i]);
            }
        }
    })
}

function clear() {
    $("#courseContainer").html("");
}

function append(classes) {
    var html = '<div class="main_box_right_content" id="' + classes.name + '" data-id="' + classes.id + '">\n' +
        '                    <h3 class="classtitle"><span id="course">' + classes.courseName + '</span>\n' +
        '                        <button class="main_box_right_content_button" onclick="selectCourses(' + classes.name + ')">选择课程</button>\n' +
        '                    </h3>\n' +
        '                    <div class="divideline"></div>\n' +
        '                    <div class="classinfo" style="margin: 0 auto;text-align:justify">\n' +
        '                        <div class="item">\n' +
        '                            <label class="itemName">班级:</label>\n' +
        '                            <label class="itemName">' + classes.name + '</label>\n' +
        '                            <label class="itemName" style="margin-left: 30%;">课程地点：</label>\n' +
        '                            <label class="itemName">' + classes.site + '</label>\n' +
        '                        </div>\n' +
        '                        <div class="item">\n' +
        '                            <label class="itemName">教师:</label>\n' +
        '                            <label class="itemName">' + classes.courseTeacher + '</label>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '                </div>'
    $("#courseContainer").append(html);
}

$("#checkButton").click(searchCourse);