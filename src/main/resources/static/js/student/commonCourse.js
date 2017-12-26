
function getCourse() {
    $.ajax({
        url: "/course/" + courseId,
        type: "GET",
        headers: {'Authorization': 'Bearer ' + localStorage.getItem("jwt")},
        success: function (data) {
            $("#courseName").text(data.name);
            $("#courseDescription").text(data.description);

        }
    });
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]);
    return null;
}

var courseId = getQueryString("courseId");
getCourse();
