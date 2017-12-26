addCourse();

function addCourse() {
    var isAdd = window.location.href.indexOf("isAdd");
    if(isAdd > 0) {
        var s = '                <div class="main_box_right_content" id="javascript">\n' +
        '                    <h3 class="classtitle"><span id="course" onclick="javascript:course(\'javascrip\')">javascript</span>\n' +
        '                        <button class="main_box_right_content_button" onclick="deleteCourse(\'javascript\')">删除课程</button>\n' +
        '                        <button class="main_box_right_content_button">修改课程</button>\n' +
        '                    </h3>\n' +
        '                    <div class="divideline"></div>\n' +
        '                    <div class="classinfo" style="margin: 0 auto;text-align:justify">\n' +
        '                        <div class="item">\n' +
        '                            <label class="itemName">班级数:</label>\n' +
        '                            <label class="itemName">1</label>\n' +
        '                            <label class="itemName" style="margin-left: 30%;">学生人数：</label>\n' +
        '                            <label class="itemName">20</label>\n' +
        '                        </div>\n' +
        '                        <div class="item">\n' +
        '                            <label class="itemName">开始时间:</label>\n' +
        '                            <label class="itemName">2017-09-22</label>\n' +
        '                            <label class="itemName" style="margin-left: 30%;">结束时间：</label>\n' +
        '                            <label class="itemName">2018-05-22</label>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '                </div>\n';
        $("#courseContainer").append(s);
    }
}