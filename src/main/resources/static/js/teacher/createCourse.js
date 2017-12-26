$("#returnButton").click(function(){
    window.history.go(-1);
});
$("#createCourse").click(function(courseId){
    proportions = {
        report: $("#report").val(),
        presentation: $("#presentation").val(),
        c: $("#c").val(),
        b: $("#b").val(),
        a: $("#a").val()
    };
    classInfo = {
        name: $("#name").val(),
        startTime: $("#startTime").val(),
        endTime: $("#endTime").val(),
        description: $("#description").val(),
        proportions: proportions
    };
    console.log(classInfo);
    $.ajax({
        url: "/course"  ,
        type: "POST",
        headers : {'Authorization':'Bearer '+localStorage.getItem("jwt")},
        data:JSON.stringify(classInfo),
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data);
            alert("创建成功!");
            window.location.href = "/teacher/course";
        }
    });
});