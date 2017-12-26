$(function () {
    var token = localStorage.getItem("jwt");
    var groupId = localStorage.getItem("groupId");
    var seminarId = localStorage.getItem("seminarId");
    console.log(groupId);
    $.ajax({
        url:"/seminar/"+seminarId+"/group",
        // url: "http://rap.taobao.org/mockjsdata/29816/seminar/32/group",
        type: "GET",
        headers: {"Authorization": "Bearer " + token},
        success: function (data) {
            //console.log(data);
            if (data.length != undefined) {
                for (var i = 0; i < data.length; i++) {
                    if (data[i].id == groupId) {
                        $("#group").html(data[i].name);
                        // console.log(data[i].name);
                    }
                }
            }
        }
    });

    $.ajax({
        url:"/group/"+groupId,
        // url: "http://rap2api.taobao.org/app/mock/933/GET/group/28",
        type: "GET",
        headers: {"Authorization": "Bearer " + token},
        success: function (data) {
            console.log(data);
            $("#leader").text(data.leader.name);
        }
    });
    //查询seminar
    $.ajax({
        // url:"/seminar/"+seminarId,
        url: "http://rap2api.taobao.org/app/mock/933/GET/seminar/32",
        type: "GET",
        headers: {"Authorization": "Bearer " + token},
        success: function (data) {
            $("#title").text(data.name);
        }
    });

    //设置小组的报告分
    $("#giveScore").click(function () {
        var score = {reportGrade: $("#grade").val()};
        console.log(score);
        $.ajax({
            url:"/group/"+groupId+"/grade/report",
            type:"PUT",
            headers: {"Authorization": "Bearer " + token},
            contentType: "application/json",
            data: JSON.stringify(score),
            success:function () {
                window.location.href = "/teacher/scoreHome";
            }
        });
    });
});