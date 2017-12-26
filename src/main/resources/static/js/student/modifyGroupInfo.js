$(".returnButton").click(function () {
    window.history.back();
});

var deleteTable = $("#deleteTable");
var addTable = $("#addTable");

var showNode = $("#showInfo");
var modifyNode = $("#modifyInfo");

init();

function init() {
    $(".deleteMember").click(function () {
        var clickNode = $(this);
        var member = clickNode.parent();
        clickNode.remove();
        member.find("td:eq(0)").remove();
        addTable.append(
            "<tr>" +
            member.html() +
            "<td class='addMember'>添加</td>" +
            "</tr>"
        );
        member.remove();
        $(".deleteMember").unbind();
        $(".addMember").unbind();
        init();
    });
    $(".addMember").click(function () {
        var clickNode = $(this);
        var member = clickNode.parent();
        clickNode.remove();
        deleteTable.append(
            "<tr>" +
            "<td>队员</td>" +
            member.html() +
            "<td class='deleteMember'>删除</td>" +
            "</tr>"
        );
        member.remove();
        $(".deleteMember").unbind();
        $(".addMember").unbind();
        init();
    });
}


function modifyOnclick() {
    // $(".addMember").forEach(function (t) {
    //     var clickNode = t;
    //     var member = t.parent();
    //     clickNode.remove();
    //     addTable.append(
    //         "<tr>" +
    //         member.html() +
    //         "<td class='deleteMember'></td>"
    //     );
    //     member.remove();
    //     init();
    // })

    showNode.toggle();
    modifyNode.toggle();
}

function submitOnclick() {

    showNode.toggle();
    modifyNode.toggle();
}

$("#submitButton").click(submitOnclick);
$("#modifyButton").click(modifyOnclick);


function getGroupDetails() {
    $.ajax({
        url: "/class/" + classId + "/classgroup",
        type: "GET",
        headers: {'Authorization': 'Bearer ' + localStorage.getItem("jwt")},
        success: function (data) {
            groupDetail = data;
            initGroup();
        }
    });
}

function initGroup() {
    var memHtml = '';
    for (i = 0; i < groupDetail.members.length; i++) {
        memHtml += '<tr>\n' +
            '<td>队员</td>\n' +
            '<td>' + groupDetail.members[i].number + '</td>\n' +
            '<td>' + groupDetail.members[i].name + '</td>\n' +
            '</tr>'
    }
    if (groupDetail.leader) {
        var leaderHtml = '<tr>\n' +
            '<td>队长</td>\n' +
            '<td>' + groupDetail.leader.number + '</td>\n' +
            '<td>' + groupDetail.leader.name + '</td>\n' +
            '</tr>'
    }
    $("#memberContainer").html(leaderHtml + memHtml);
}

var groupDetail;
var classId = getQueryString("classId");
getGroupDetails();