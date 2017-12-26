window.onload = course();
function course(){
    $.ajax({
        url: "/course",
        type: "GET",
        headers : {'Authorization':'Bearer '+localStorage.getItem("jwt")},
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data.length);
            html = "";
            for(i=0;i<data.length;i++){
                console.log(data[i].startTime);
                console.log(getMyDate(data[i].endTime));
                // console.log(new (data[i].endTime).toLocaleDateString());
                html +=
                    '                <div class="main_box_right_content" id="'+data[i].id+'">\n' +
                    '                    <h3 class="classtitle"><span id="course" onclick="javascript:courseInfo(\''+ data[i].id +'\')">'+ data[i].name +'</span>\n' +
                    '                        <button class="main_box_right_content_button" onclick="deleteCourse(\''+ data[i].id +'\')">删除课程</button>\n' +
                    '                        <button class="main_box_right_content_button" data-toggle="modal" data-target="#myModal'+ data[i].id +'">修改课程</button>\n' +
                    '                        <div class="modal fade" id="myModal'+ data[i].id +'" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">\n' +
                    '                            <div class="modal-dialog">\n' +
                    '                                <div class="modal-content">\n' +
                    '                                    <div class="modal-header">\n' +
                    '                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>\n' +
                    '                                        <h4 class="modal-title" id="myModalLabel">修改课程</h4>\n' +
                    '                                    </div>\n' +
                    '                                    <div class="modal-body">\n' +
                    '                                        <div class="item">\n' +
                    '                                            <label style="font-family: YouYuan;font-size: 15px;width:30%;">课程名称：</label>\n' +
                    '                                            <input style="width:60%;border:1px solid black;min-height: 8px;" id = "name'+data[i].id+'"; value="'+ data[i].name +'">\n' +
                    '                                        </div>\n' +
                    '                                        <div class="item">\n' +
                    '                                            <label style="font-family: YouYuan;font-size: 15px;width:30%;">课程描述：</label>\n' +
                    '                                            <input style="width:60%;border:1px solid black;min-height: 8px;" id = "description'+data[i].id+'"; value="'+ data[i].description +'">\n' +
                    '                                        </div>\n' +
                    '                                        <div class="item">\n' +
                    '                                            <label style="font-family: YouYuan;font-size: 15px;width:30%;">讨论课占比：</label>\n' +
                    '                                            <input style="width:60%;border:1px solid black;min-height: 8px;" id="presentation'+data[i].id+'" value="'+ data[i].presentationPercentage +'">\n' +
                    '                                        </div>\n' +
                    '                                        <div class="item">\n' +
                    '                                            <label style="font-family: YouYuan;font-size: 15px;width:30%;">报告占比：</label>\n' +
                    '                                            <input style="width:60%;border:1px solid black;min-height: 8px;" id="report'+data[i].id+'" value="'+ data[i].reportPercentage +'">\n' +
                    '                                        </div>\n' +
                    '                                        <div class="item">\n' +
                    '                                            <label style="font-family: YouYuan;font-size: 15px;width:30%;">5分占比：</label>\n' +
                    '                                            <input style="width:60%;border:1px solid black;min-height: 8px;" id="c'+data[i].id+'" value="'+ data[i].fivePointPercentage +'">\n' +
                    '                                        </div>\n' +
                    '                                        <div class="item">\n' +
                    '                                            <label style="font-family: YouYuan;font-size: 15px;width:30%;">4分占比：</label>\n' +
                    '                                            <input style="width:60%;border:1px solid black;min-height: 8px;" id="b'+data[i].id+'" value="'+ data[i].fourPointPercentage +'">\n' +
                    '                                        </div>\n' +
                    '                                        <div class="item">\n' +
                    '                                            <label style="font-family: YouYuan;font-size: 15px;width:30%;">3分占比：</label>\n' +
                    '                                            <input style="width:60%;border:1px solid black;min-height: 8px;" id="a'+data[i].id+'" value="'+ data[i].threePointPercentage +'">\n' +
                    '                                        </div>\n' +
                    '                                        <div class="item">\n' +
                    '                                            <label style="font-family: YouYuan;font-size: 15px;width:30%;">开始时间：</label>\n' +
                    '                                            <input style="width:60%;border:1px solid black;min-height: 8px;" id="startTime'+data[i].id+'" value="'+ getMyDate(data[i].startDate) +'">\n' +
                    '                                        </div>\n' +
                    '                                        <div class="item">\n' +
                    '                                            <label style="font-family: YouYuan;font-size: 15px;width:30%;">结束时间：</label>\n' +
                    '                                            <input style="width:60%;border:1px solid black;min-height: 8px;" id="endTime'+data[i].id+'"value="'+ getMyDate(data[i].endDate) +'">\n' +
                    '                                        </div>\n' +
                    '                                    </div>\n' +
                    '                                    <div class="modal-footer">\n' +
                    '                                        <button type="button" class="btn btn-default" data-dismiss="modal" >关闭</button>\n' +
                    '                                        <button type="button" class="btn btn-primary" id="submitButton" onclick="javascript:modifyCourse(\''+data[i].id+'\')">提交</button>\n' +
                    '                                    </div>\n' +
                    '                                </div><!-- /.modal-content -->\n' +
                    '                            </div><!-- /.modal -->\n' +
                    '                        </div>\n' +
                    '                    </h3>\n' +
                    '                    <div class="divideline"></div>\n' +
                    '                    <div class="classinfo" style="margin: 0 auto;text-align:justify">\n' +
                    '                        <div class="item">\n' +
                    '                            <label class="itemName">开始时间:</label>\n' +
                    '                            <label class="itemName">'+getMyDate(data[i].startDate)+'</label>\n' +
                    '                            <label class="itemName" style="margin-left: 30%;">结束时间：</label>\n' +
                    '                            <label class="itemName">'+getMyDate(data[i].endDate)+'</label>\n' +
                    '                        </div>\n' +
                    '                    </div>\n' +
                    '                </div>\n' ;


            }
            $("#courseContainer").html(html);
        }
    });
};
function deleteCourse(e) {
    var r = confirm("确认删除?");
    if( r == true){
        $.ajax({
            url: "/course/"+e,
            type: "DELETE",
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                console.log(data);
                alert("删除成功");
                $("#"+e).remove();
            }
        });
    }
};
function getMyDate(str){
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth()+1,
        oDay = oDate.getDate(),
        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) ;//最后拼接时间
    return oTime;
};
//补0操作
function getzf(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
};
function modifyCourse(e){
    proportions = {
        report: $("#report"+e).val(),
        presentation: $("#presentation"+e).val(),
        c: $("#c"+e).val(),
        b: $("#b"+e).val(),
        a: $("#a"+e).val()
    };
    classInfo = {
        name: $("#name"+e).val(),
        startTime: $("#startTime"+e).val(),
        endTime: $("#endTime"+e).val(),
        description: $("#description"+e).val(),
        proportions: proportions
    };
    $.ajax({
        url: "/course/"+e,
        type: "PUT",
        headers : {'Authorization':'Bearer '+localStorage.getItem("jwt")},
        data: JSON.stringify(classInfo),
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data);
            alert("修改成功");
            window.location.href = "/teacher/course";
        }
    });
}
$("#returnButton").click(function(){
    window.location.href = "/teacher/course/home?courseId="+courseId;
});

function courseInfo(e){
  window.location.href = "/teacher/course/home?courseId="+e;
};
