$(function () {
    //获取课程
    var token = localStorage.getItem("jwt");
    $.ajax({
        url:"/course",
        // url:"http://rap.taobao.org/mockjsdata/29816/course1",
        headers: {"Authorization": "Bearer " + token},
        type:"GET",
        success:function (data) {
            console.log(data);
            if(data.length != undefined){
                for(var i=0;i<data.length;i++){
                    $(".content").append("<div class=\"main_box_right_content\" id="+data[i].id+">\n" +
                        "            <h3 class=\"classtitle\">"+data[i].name+"\n" +
                        "                <button id='deleteCourse'>删除课程</button>\n" +
                        "                <button id='modifyCourse'>修改课程</button>\n" +
                        "            </h3>\n" +
                        "            <div class=\"divideline\"></div>\n" +
                        "            <div class=\"classinfo\">\n" +
                        "                <table class=\"table\">\n" +
                        "                    <tr>\n" +
                        "                        <td class=\"tabletext\">班级数："+data[i].numClass+"</td>\n" +
                        "                        <td class=\"tabletext\">学生人数："+data[i].numStudent+"</td>\n" +
                        "                    </tr>\n" +
                        "                    <tr>\n" +
                        "                        <td class=\"tabletext\">开始时间："+data[i].startTime+"</td>\n" +
                        "                        <td class=\"tabletext\">结束时间: "+data[i].endTime+"</td>\n" +
                        "                    </tr>\n" +
                        "                </table>\n" +
                        "            </div>\n" +
                        "        </div>")
                }
                //写入加号
                $(".content").append(" <div class=\"main_box_right_content\" id='addCourse'>\n" +
                    "            <img class=\"addcourse\" src=\"/img/add.png\">\n" +
                    "\n" +
                    "        </div>")
            }
        }
    })

    //点击修改课程
    $(".body").on("click","#modifyCourse",function () {
        var courseId = $(this).parent().parent().attr("id");
        console.log(courseId);
        localStorage.setItem("courseId",courseId);
        window.location.href = "/teacher/updateCourse";
    })

    //点击删除课程
    $(".body").on("click","#deleteCourse",function () {
        var courseId = $(this).parent().parent().attr("id");
        if(confirm("确认删除该课程")){
            $.ajax({
                url:"/course/"+courseId,
                type:"DELETE",
                headers: {"Authorization": "Bearer " + token},
                success:function () {
                    $("#"+courseId).hide();
                },
                error:function () {
                    alert("删除课程失败")
                }
            })
        }
    })

    //跳转到课程内页面
    $(".body").on("click",".classinfo",function () {
        var courseId = $(this).parent().attr("id");
        console.log(courseId);
        localStorage.setItem("courseId",courseId);
        window.location.href = "/teacher/courseInformation";
    })

    //跳转到添加课程界面
    $(".body").on("click","#addCourse",function () {
        var courseId = $(this).parent().parent().attr("id");
        console.log(courseId);
        localStorage.setItem("courseId",courseId);
        window.location.href = "/teacher/createCourse";
    })


})