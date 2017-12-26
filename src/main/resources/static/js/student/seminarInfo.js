$(".returnButton").click(function () {
    window.history.back();
});

function topic(id) {
    window.location.href = "/student/course/topicInfo?courseId=" + courseId +
        "&seminarId=" + seminarId + "&topicId=" + id + "&groupingMethod=" + groupingMethod;
};
$("#grade").click(function () {
    window.location.href = "/student/course/grade";
})


function getSeminar() {
    $.ajax({
        url: "/seminar/" + seminarId,
        type: "GET",
        headers: {'Authorization': 'Bearer ' + localStorage.getItem("jwt")},
        success: function (data) {
            $("#seminarName").text(data.name);
            $("#seminarDescription").text(data.description);
            $("#seminarGroupingMethod").text(data.groupingMethod == "fixed" ?
                "固定分组" : "随机分组");
            $("#seminarStartTime").text((new Date(data.startTime).toLocaleDateString()));
            $("#seminarEndTime").text((new Date(data.endTime)).toLocaleDateString());
            groupingMethod = data.groupingMethod;
            for (i = 0; i < data.topics.length; i++) {
                var html = '<div class="block" onclick="topic(' + data.topics[i].id + ')">\n' +
                    '<div class="blockFont">' + data.topics[i].name + '</div>\n' +
                    '</div>'
                $("#topicContainer").append(html);
            }
        }
    })
}

var seminarId = getQueryString("seminarId");
var groupingMethod;
getSeminar();