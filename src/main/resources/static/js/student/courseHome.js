$("#returnButton").click(function () {
    window.location.href = "/student/course";
});

function seminar(i) {
    // if (seminars[i].groupingMethod == "fixed") {
    //     window.location.href = "/student/course/seminarInfo/fixed";
    // }
    // if (seminars[i].groupingMethod == "random") {
    //     window.location.href = "/student/course/seminarInfo/random";
    // }
    window.location.href = "/student/course/seminarInfo?courseId=" +
        courseId + "&seminarId=" + seminars[i].id;
};
$("#group").click(function () {
    window.location.href = "/student/course/group";
});

function getSeminar() {
    $.ajax({
        url: "/course/" + courseId + '/seminar',
        type: "GET",
        headers: {'Authorization': 'Bearer ' + localStorage.getItem("jwt")},
        success: function (data) {
            seminars = data;
            for (i = 0; i < data.length; i++) {
                appendSeminar(data[i], i);
            }
        }
    });
}


function appendSeminar(seminar, i) {
    var html = '<div class="block" onclick="seminar(' + i + ')">\n' +
        '<div class="blockFont">' + seminar.name + '</div>\n' +
        '</div>'
    $("#seminarContainer").append(html);
}


var seminars;
getSeminar();
