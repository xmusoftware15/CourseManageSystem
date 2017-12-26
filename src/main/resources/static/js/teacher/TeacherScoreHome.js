$(function () {
    var token = localStorage.getItem("jwt");
    var seminarId = localStorage.getItem("seminarId");
    console.log(seminarId);

    var courseJson = localStorage.getItem("courseJson");
    courseJson = JSON.parse(courseJson);
    $(".courseName").text(courseJson.name);
    $(".courseIntroduction").text(courseJson.description);

    //获取所有小组信息
    $.ajax({
        url:"/seminar/"+seminarId+"/group",
        // url: "http://rap.taobao.org/mockjsdata/29816/seminar/32/group",
        type: "GET",
        headers: {"Authorization": "Bearer " + token},
        success: function (data) {
            console.log(data);
            if (data.length != undefined) {
                for (var i = 0; i < data.length; i++) {
                    $(".tableContent").append("  <tr>\n" +
                        "                    <td>" + data[i].name[1] + "</td>\n" +
                        "                    <td>" + data[i].name + "</td>\n" +
                        "                    <td>XXX</td>\n" +
                        "                    <td>5</td>\n" +
                        "                    <td>已提交</td>\n" +
                        "                    <td>5</td>\n" +
                        "                    <td>5</td>\n" +
                        "                    <td>\n" +
                        "                         <img src=\"/img/view.png\" alt=\"预览\" name='report' id=" + data[i].id + ">\n" +
                        "                        <img src=\"/img/download.png\" alt=\"下载\" name='download' id=" + data[i].id + ">\n" +
                        "                    </td>\n" +
                        "                </tr>")
                }
            }

        }
    })

    //跳转到pdf预览界面
    $(".body").on("click", "[name=report]", function () {
        var groupId = $(this).attr("id");
        // console.log(groupId);
        localStorage.setItem("groupId", groupId);
        window.location.href = "/teacher/scoreReport";
    })

    //下载文档
    $(".body").on("click", "[name=download]", function () {
        var groupId = $(this).attr("id");
        console.log(groupId);
        //todo
        //下载功能
    })

    //返回上一页
    $(".returnButton").click(function () {
        window.history.back();
    })
})