function string2date(str){
    return new Date(Date.parse(str.replace(/-/g,  "/")));
}
$(function () {
    var token = localStorage.getItem("jwt");
    var topicId = localStorage.hasOwnProperty("topicId") ? localStorage.getItem("topicId") : 1;
    var courseJson = localStorage.getItem("courseJson");
    if (courseJson != null) {
        courseJson = JSON.parse(courseJson);
        $(".courseName").text(courseJson.name);
        $(".courseIntroduction").text(courseJson.description);
    }
    //查询topic时间是否已经开始，进而决定按钮的隐藏与开启,
    var seminarJson = localStorage.getItem("seminarJson");
    if (seminarJson != null) {
        seminarJson = JSON.parse(seminarJson);
        console.log(seminarJson);
        var startTime = string2date(seminarJson.startTime);
        // var startTime = string2date("2018-8-8");
        var nowData = new Date();
        if(startTime<nowData){
            //讨论课已经开始
            $("#modify").hide();
            $("#delete").hide()
        }
    }

    //填充页面信息
    $.ajax({
        url:"/topic/"+topicId,
        // url:"http://rap2api.taobao.org/app/mock/933/GET/topic/257",
        type:"GET",
        headers: {"Authorization": "Bearer " + token},
        success:function (data) {
            console.log(data);
            //存储topicJson
            var json = JSON.stringify(data);
            localStorage.setItem("topicJson", json);


            $("#name").text(data.name);
            $("#desc").text(data.description);
            $("#groupLimit").text(data.groupNumberLimit);
            $("#groupMemberLimit").text(data.groupStudentLimit);

            //查询多少小组选择了该topic
            //查询多少小组选择了该topic
            $.ajax({
                // url:"/topic/"+topicId+"/group",
                url:"http://rap.taobao.org/mockjsdata/29816/topic/257/group",
                type:"GET",
                headers: {"Authorization": "Bearer " + token},
                success:function (data) {
                    if(data.length != undefined){
                        $("#groupChooseNum").text(data.length);
                    }
                }
            })
        }
    })

    //返回上一页
    $(".returnButton").click(function () {
        window.history.back();
    })

    //点击了话题修改
    $(".body").on("click","#modify",function () {
        window.location.href = "/teacher/updateTopic";
    })

    //点击了删除话题
    $(".body").on("click","#delete",function () {
        if(confirm("确定要删除该话题？")){
            $.ajax({
                url:"/topic/"+topicId,
                type:"DELETE",
                headers: {"Authorization": "Bearer " + token},
                success:function () {
                    alert("成功删除话题");
                    window.location.href = "/teacher/seminarInfo";
                },
                error:function () {
                    alert("删除话题失败");
                }
            })
        }
    })
})