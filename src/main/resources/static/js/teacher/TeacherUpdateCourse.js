var courseId = localStorage.getItem("courseId");
console.log(courseId);

function getInfo() {
    $.ajax({
        url:"/course/"+courseId,
        // url:"http://rap2api.taobao.org/app/mock/933/GET/course/23",
        type:"GET",
        success:function (data) {
            console.log(data);
            $("#coursename").val(data.name);
            $("#courseinfo").val(data.description);
        }
    })
}
$(function () {

    getInfo();
    $("#submit").on("click", function () {
        var course = {
            name: $("#coursename").val(),
            description: $("#courseinfo").val(),
            startTime: $("#begintime").val(),
            endTime: $("#endtime").val(),
            proportions: {
                report: $("#reportscore").val(),
                presentation: $("#seminarscore").val(),
                c: $("#three").val(),
                b: $("#four").val(),
                a: $("#five").val()
            }
        };
        $.ajax({
            url: "/course/" + courseId,
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(course),
            success: function (data) {
                alert("success!");
                window.location.href = "/teacher/courseHome"
            }
        });
    });

    $("#reset").on("click", function () {
        $("input, textarea").val("");
    })
})