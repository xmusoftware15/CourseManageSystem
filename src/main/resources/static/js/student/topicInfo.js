$(".returnButton").click(function () {
    window.history.back();
});
function initSelect() {
    $("#choose").click(function () {
        var r = confirm("确认选择？");
        if (r == true) {
            $.ajax({
                url: ""
            })
            $("#chooseButton").html(
                "<button class=" + "submit" + " onclick=" + "deleteTopic()" + ">" + "取消选择" + "</button>" +
                "<div class=" + "clear" + ">" + "</div>"
            );
        }
    });
}


function deleteTopic() {
    var r = confirm("确认取消？");
    if (r == true) {
        alert("取消成功");
        $("#chooseButton").html(
            "<button class=" + "submit" + " id=" + "choose" + ">" + "选择话题" + "</button>" +
            "<div class=" + "clear" + ">" + "</div>"
        );
    }
};

function getTopic() {
    $.ajax({
        url: "/topic/" + topicId,
        type: "GET",
        headers: {'Authorization': 'Bearer ' + localStorage.getItem("jwt")},
        success: function (data) {
            $("#topicName").text(data.name);
            $("#topicDescription").text(data.description);
            $("#topicGroupLimit").text(data.groupLimit);
            $("#topicGroupMemberLimit").text(data.groupMemberLimit);
            if (groupingMethod == "fixed") {
                var html = '<div class="item" id="chooseButton">\n' +
                    '<button class="submit" id="choose">选择话题</button>\n' +
                    '<div class="clear"></div>\n' +
                    '</div>';
                $("#mainContainer").append(html);
                initSelect();
            }
        }
    })
}

var topicId = getQueryString("topicId");
var groupingMethod = getQueryString("groupingMethod");
getTopic();