<#import "*/frame.ftl" as frame/>
    <#global url = springMacroRequestContext.getContextPath()>
<@frame.page title="查看话题">
<link rel="stylesheet" href="/css/content.css">
<div class="content">
    <div class="contentBlock">
        <div class="title">查看话题</div>
        <div class="returnButton" id="returnButton">返回上一页</div>
        <div class="line"></div>
        <div class="itemBody" id="showInfo">
            <div class="item">
                <label class="itemName">题 目:</label>
                <label class="itemName" id="name"></label>
            </div>
            <div class="item">
                <label class="itemName">说 明:</label>
                <label class="itemName" id="description"></label>
            </div>
            <div class="item">
                <label class="itemName">组数限制:</label>
                <label class="itemName" id="groupLimit"></label>
            </div>
            <div class="item">
                <label class="itemName">组内人数限制:</label>
                <label class="itemName" id="memberLimit"></label>
            </div>
            <div class="item">
                <label class="itemName">已选小组:</label>
                <label class="itemName" id="group"></label>
            </div>
            <div class="item" id="deleteAndUpdate">
                <button class="submit" id="modifyButton">修改</button>
                <button class="reset" onclick="deleteTopics()">删除话题</button>
                <div class="clear"></div>
            </div>
        </div>
        <div class="itemBody" id="modifyInfo" style="display: none;">
            <div class="item">
                <label class="itemName">题 目:</label>
                <input class="middleInput" id="nameM">
            </div>
            <div class="item">
                <label class="itemName">说 明:</label>
                <input class="middleInput" id="descriptionM">
            </div>
            <div class="item">
                <label class="itemName">组数限制:</label>
                <input class="middleInput" id="groupLimitM">
            </div>
            <div class="item">
                <label class="itemName">组内人数限制:</label>
                <input class="middleInput" id="memberLimitM">
            </div>
            <div class="item">
                <label class="itemName">已选小组:</label>
                <label class="itemName" id="groupM"></label>
            </div>
            <div class="item">
                <button class="submit" id="submitButton">保存</button>
                <button class="reset" >删除话题</button>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
<script>
    function returnButton(name,url) {
        console.log(url);
        console.log(name);
    }
</script>
<script src="/js/teacher/topicInfo.js"></script>
<script src="/js/teacher/modifyTopicInfo.js"></script>
<script src="/js/teacher/courseCommon.js"></script>
</@frame.page>