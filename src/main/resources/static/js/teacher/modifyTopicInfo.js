var topicInfo = getInfo();
var showNode = $("#showInfo");
var modifyNode = $("#modifyInfo");
function getInfo() {
    return {
        "name": $("label#name").text(),
        "description": $("label#description").text(),
        "groupLimit": $("label#groupLimit").text(),
        "memberLimit": $("label#memberLimit").text()
    };
}

function modifyOnclick() {
    $("input#name").val(topicInfo.name);
    $("input#description").val(topicInfo.description);
    $("input#groupLimit").val(topicInfo.groupLimit);
    $("input#memberLimit").val(topicInfo.memberLimit);

    showNode.toggle();
    modifyNode.toggle();
}

function submitOnclick() {
    topicInfo = {
        "name": $("input#name").val(),
        "description": $("input#description").val(),
        "groupLimit": $("input#groupLimit").val(),
        "memberLimit" : $("input#memberLimit").val()
    };

    $("label#name").text(topicInfo.name);
    $("label#description").text(topicInfo.description);
    $("label#groupLimit").text(topicInfo.groupLimit);
    $("label#memberLimit").text(topicInfo.memberLimit);
    showNode.toggle();
    modifyNode.toggle();
}

$("#submitButton").click(submitOnclick);
$("#modifyButton").click(modifyOnclick);