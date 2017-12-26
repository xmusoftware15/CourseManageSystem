<#import "*/frame.ftl" as frame/>
<@frame.page title="查看话题">
<link rel="stylesheet" href="/css/content.css">
<div class="content">
    <div class="contentBlock">
        <div class="title">查看话题</div>
        <div class="returnButton">返回上一页</div>
        <div class="line"></div>
        <div class="itemBody" id="mainContainer">
            <div class="item">
                <label class="itemName">题 目:</label>
                <label class="itemName" id="topicName"></label>
            </div>
            <div class="item">
                <label class="itemName">说 明:</label>
                <label class="itemName" id="topicDescription"></label>
            </div>
            <div class="item">
                <label class="itemName">组数限制:</label>
                <label class="itemName" id="topicGroupLimit"></label>
            </div>
            <div class="item">
                <label class="itemName">组内人数限制:</label>
                <label class="itemName" id="topicGroupMemberLimit"></label>
            </div>
        <#--<#if seminar.groupingMethod == "固定分组">-->
            <div class="item" id="chooseButton">
                <button class="submit" id="choose">选择话题</button>
                <div class="clear"></div>
            </div>
        <#--</#if>-->
        </div>
    </div>
</div>
<script src="/js/student/commonCourse.js"></script>
<script src="/js/student/topicInfo.js"></script>
<script>

</script>
</@frame.page>