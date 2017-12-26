<#import "*/frame.ftl" as frame/>
<@frame.page title="创建讨论课">
<link rel="stylesheet" href="/css/content.css">
<div class="content">
    <div class="contentBlock">
        <div class="title">创建讨论课</div>
        <div class="returnButton" id="returnButton">返回上一页</div>
        <div class="line"></div>
        <div class="itemBody">
            <div class="item">
                <label class="itemName">讨论课名称</label>
                <input class="bigInput" id="name">
            </div>
            <div class="item" style="height: 30%">
                <label class="itemName">讨论课说明:</label>
                <textarea class="textStyle" id="description"></textarea>
            </div>
            <div class="item">
                <label class="itemName">分组方式:</label>
                <select class="middleInput" id="group">
                    <option>固定分组</option>
                    <option>随机分组</option>
                </select>
            </div>
            <div class="item">
                <label class="itemName">开始时间：</label>
                <input class="timeStyle" type="date" id="startTime">
                <label class="itemName">结束时间：</label>
                <input class="timeStyle" type="date" id="endTime">
            </div>
            <div class="item">
                <button class="submit" id="submitButton">提交</button>
                <button class="reset">重置</button>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
<script src="/js/teacher/createSeminar.js"></script>
<script src="/js/teacher/courseCommon.js"></script>
</@frame.page>