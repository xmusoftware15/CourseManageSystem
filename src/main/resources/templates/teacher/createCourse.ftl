<#import "*/teacherFrame.ftl" as frame/>
<@frame.page title="创建课程">
<link rel="stylesheet" href="/css/content.css">
<div class="content">
    <div class="contentBlock">
    <div class="title">创建课程</div>
    <div class="returnButton" >返回上一页</div>
    <div class="line"></div>
    <div class="itemBody">
        <div class="item">
            <label class="itemName">课程名称:</label>
            <input class="bigInput" id="name">
        </div>
        <div class="item">
            <label class="itemName">开始时间：</label>
            <input class="timeStyle" type="date" id="startTime">
            <label class="itemName">结束时间：</label>
            <input class="timeStyle" type="date" id="endTime">
        </div>
        <div class="item" style="height: 30%">
            <label class="itemName">说 明:</label>
            <textarea class="textStyle" id="description">xxxx</textarea>
        </div>
        <div class="item">
            <label class="itemName">评分规则:</label>
            <label class="itemName">讨论课分数占比</label>
            <input class="smallInput" id="presentation">
            <label class="itemName"> 报告分数占比</label>
            <input class="smallInput" id="report">
        </div>
        <div class="item" style="margin-left: 25%">
            <label class="smallItemName">5分占比</label>
            <input type="text" name="seminarGrade" class="smallInput" id="c">
            <label class="smallItemName">4分占比</label>
            <input type="text" name="seminarGrade" class="smallInput" id="b">
            <label class="smallItemName">3分占比</label>
            <input type="text" name="seminarGrade" class="smallInput" id="a">
        </div>
        <div class="item">
            <button class="submit" id="createCourse">提交</button>
            <button class="reset">重置</button>
            <div class="clear"></div>
        </div>
    </div>
</div>
</div>
<div class="clear"></div>
<script src="/js/teacher/createCourse.js"></script>
</@frame.page>