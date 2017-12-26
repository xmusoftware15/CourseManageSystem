function course(id) {
    window.location.href = "/student/course/home?courseId=" + id;
};

function deleteCourse(e) {
    var r = confirm("确认退课？");
    if (r == true) {
        var classId = $(e).data("id");
        $.ajax({
            url: "/class/" + classId + "/student/" + localStorage.getItem("userId"),
            type: "DELETE",
            headers: {'Authorization': 'Bearer ' + localStorage.getItem("jwt")},
            contentType: "application/json",
            success: function (data, status, xhr) {
                alert("退课成功");
                $(e).remove();
            }
        });
    }
};

function appendCourse(course) {
    var html = '    <div class="main_box_right_content" id="' + course.name + '" data-id="' + course.id + '">\n' +
        '                    <h3 class="classtitle"><span id="course" onclick="course(' + course.id + ')">' + course.name + '<span>\n' +
        '                    </h3>\n' +
        '                        <button class="main_box_right_content_button" onclick="deleteCourse(' + course.name + ')">退选课程</button>\n' +
        '                    <div class="divideline"></div>\n' +
        '                    <div class="classinfo" style="margin: 0 auto;text-align:justify">\n' +
        '                        <div class="item">\n' +
        '                            <label class="itemName">班级:</label>\n' +
        '                            <label class="itemName">' + course.name + '</label>\n' +
        '                            <label class="itemName" style="margin-left: 30%;">课程地点：</label>\n' +
        '                            <label class="itemName">' + course.site + '</label>\n' +
        '                        </div>\n' +
        '                        <div class="item">\n' +
        '                            <label class="itemName">教师:</label>\n' +
        '                            <label class="itemName">' + course.course.teacher.name + '</label>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '                </div>';
    $("#courseContainer").append(html);
}

function getCourse() {
    $.ajax({
        url: "/course",
        type: "GET",
        headers: {'Authorization': 'Bearer ' + localStorage.getItem("jwt")},
        success: function (data) {
            for (i = 0; i < data.length; i++)
                appendCourse(data[i]);
        }
    })
}

getCourse();