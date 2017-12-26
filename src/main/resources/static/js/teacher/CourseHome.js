$(document).ready(function () {
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);  //获取url中"?"符后的字符串并正则匹配
        console.log(r);
        var context = "";
        if (r != null)
            context = r[2];
        context = decodeURI(context);
        reg = null;
        r = null;
        return context == null || context == "" || context == "undefined" ? "" : context;
    };

    courseId = GetQueryString("courseId");
    GetClassesInfo(courseId);
    GetSeminarInfo(courseId);
    getCourseInfo(courseId);


});
var courseId;
function GetClassesInfo(courseId) {
    $.ajax({
        url: "/course/" + courseId + "/class",
        type: "GET",
        headers: {'Authorization': 'Bearer ' + localStorage.getItem("jwt")},
        success: function (data) {
            console.log(data);
            console.log(data.length);
            var html = "";
            for (i = 0; i < data.length; i++) {
                html += '<div class="block" id="' + data[i].id +
                    '" onclick="classInfo(\'' + data[i].id + '\')">' +
                    '<div class="blockFont">' + data[i].name +
                    ' </div>' + '</div>';
            }
            $("#blockBody").prepend(html);
        }
    });
}

function GetSeminarInfo(courseId) {
    param = {
        embedGrade: false
    };
    $.ajax({
        url: "/course/" + courseId + "/seminar",
        type: "GET",
        headers: {'Authorization': 'Bearer ' + localStorage.getItem("jwt")},
        data: {embedGrade: false},
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data);
            console.log(data.length);
            var html = "";
            for (i = 0; i < data.length; i++) {
                html +=
                    '<div class="block" id = "' + data[i].id + '" onclick="seminar(\'' + data[i].id + '\')">' +
                    '<div class="blockFont">' + data[i].name + '</div>' +
                    '</div>';
            }
            $("#seminarInfo").prepend(html);
        }
    });
};

$("#classAdd").click(function () {
    window.location.href = "/teacher/course/createClass?courseId="+courseId;
});

$("#seminarAdd").click(function () {
    window.location.href = "/teacher/course/createSeminar?courseId="+courseId;
});

function seminar(e) {
    window.location.href = "/teacher/course/seminarInfo?seminarId="+e+"&courseId="+courseId;
};

function classInfo(e) {
    window.location.href = "/teacher/course/classInfo?classId="+e+"&courseId="+courseId;
};

$("#returnButton").click(function () {
    window.location.href = "/teacher/course";
});
