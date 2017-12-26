addTopic();

function addTopic() {
    var isAdd = window.location.href.indexOf("isAddTopic");
    if(isAdd > 0) {
        var s = '                <div class="block" id="C" onclick="topicInfo(\'C\',\'C\')">\n' +
            '                    <div class="blockFont">C</div>\n' +
            '                </div>\n';
        $("#topicAdd").before(s);
    }
}