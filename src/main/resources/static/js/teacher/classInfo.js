$(document).ready(function () {
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);  //获取url中"?"符后的字符串并正则匹配
        console.log(r);
        var context = "";
        if (r != null)
            context = r[2];
        context = decodeURI(context);
        reg = null;
        r = null;
        return context == null || context == "" || context == "undefined" ? "" : context;
    }

    var classId = GetQueryString("classId");
    courseId = GetQueryString("courseId");
    console.log(classId);
    GetClassesInfo(classId);

    $("#submitButton").click(function () {
        proportions = {
            presentation: $("#presentationM").val(),
            report: $("#reportM").val(),
            c: $("#cM").val(),
            b: $("#bM").val(),
            a: $("#aM").val()
        };
        classinfo = {
            name : $("#classNameM").val(),
            site: $("#siteM").val(),
            time: $("#week").find("option:selected").text() + $("#day").find("option:selected").text()+$("#detail").find("option:selected").text(),
            proportions: proportions,
            calling: null,
            roster: null
        };
        $.ajax({
            url: "/class/" + classId,
            type: "PUT",
            data: JSON.stringify(classinfo),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                location.reload();
                alert("修改成功！");
            }
        });
    });
});
var id;
var info;
var courseId;
function GetClassesInfo(classId) {
    console.log(classId);
    $.ajax({
        url: "/class/" + classId,
        type: "GET",
        success: function (data) {
            console.log(data);
            console.log(data.length);
            id = data.id;
            info = data;
            $("#className").html(data.name);
            $("#classSite").html(data.site);
            $("#classTime").html(data.time);
            $("#classSeminar").html(data.proportions.presentation);
            $("#classReport").html(data.proportions.report);
            html = '<tr>\n' +
                '                        <td>5</td>\n' +
                '                        <td>' + data.proportions.c + '</td>\n' +
                '                    </tr>' +
                '<tr>\n' +
                '                        <td>4</td>\n' +
                '                        <td>' + data.proportions.b + '</td>\n' +
                '                    </tr>'+
            '<tr>\n' +
            '                        <td>3</td>\n' +
            '                        <td>' + data.proportions.a + '</td>\n' +
            '                    </tr>';
            $("#gradeMethon").html(html);
        }
    });
}

function deleteClass(){
    $.ajax({
        url: "/class/" + id,
        type: "DELETE",
        headers: {'Authorization': 'Bearer ' + localStorage.getItem("jwt")},
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            alert("删除成功！");
            window.history.go(-1);
        }
    });
}


$("#modifyButton").click(function () {
    $("#classNameM").val(info.name);
    $("#siteM").val(info.site);
    $("#classTimeM").val(info.time);
    $("#presentationM").val(info.proportions.presentation);
    $("#reportM").val(info.proportions.report);
    $("#cM").val(info.proportions.c);
    $("#bM").val(info.proportions.b);
    $("#aM").val(info.proportions.a);
});

$("#returnButton").click(function () {
    window.location.href = "/teacher/course/home?courseId="+courseId;
});