var seminarInfo = getInfo();
var showNode = $("#showInfo");
var modifyNode = $("#modifyInfo");
function getInfo() {
    return {
        "name": $("label#name").text(),
        "description": $("label#description").text(),
        "groupingMethod": $("label#groupingMethod").text(),
        "startTime": $("label#startTime").text(),
        "endTime" : $("label#endTime").text()
    };
}

function modifyOnclick() {
    $("input#name").val(seminarInfo.name);
    $("input#description").val(seminarInfo.description);
    $("input#groupingMethod").val(seminarInfo.groupingMethod);
    $("input#startTime").val(seminarInfo.startTime);
    $("input#endTime").val(seminarInfo.endTime);

    showNode.toggle();
    modifyNode.toggle();
}

function submitOnclick() {
    seminarInfo = {
        "name": $("input#name").val(),
        "description": $("input#description").val(),
        "groupingMethod": $("input#groupingMethod").val(),
        "startTime": $("input#startTime").val(),
        "endTime" : $("input#endTime").val()
    };

    $("label#name").text(seminarInfo.name);
    $("label#description").text(seminarInfo.description);
    $("label#groupingMethod").text(seminarInfo.groupingMethod);
    $("label#title").text(seminarInfo.title);
    $("label#email").text(seminarInfo.email);
    showNode.toggle();
    modifyNode.toggle();
}

$("#submitButton").click(submitOnclick);
$("#modifyButton").click(modifyOnclick);