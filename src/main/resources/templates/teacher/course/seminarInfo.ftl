<#import "*/frame.ftl" as frame/>
<@frame.page title="讨论课信息">
<link rel="stylesheet" href="/css/content.css">
<div class="content">
    <div class="contentBlock" style="height:60%">
        <div class="title" id="titleName"></div>
        <div class="returnButton" id="returnButton">返回上一页</div>
        <div class="line"></div>
        <div class="itemBody" id="showInfo">
            <div class="item">
                <label class="itemName">讨论课名称：</label>
                <label class="itemName" id="name"></label>
            </div>
            <div class="item">
                <label class="itemName">讨论课说明:</label>
                <label class="itemName" id="description"></label>
            </div>
            <div class="item">
                <label class="itemName">分组方式:</label>
                <label class="itemName" id="groupingMethod"></label>
            </div>
            <div class="item">
                <label class="itemName">开始时间</label>
                <label class="itemName" id="startTime"></label>
            </div>
            <div class="item">
                <label class="itemName">结束时间</label>
                <label class="itemName" id="endTime"></label>
            </div>
            <div class="item">
                <button class="middleButton" id="modifyButton">修改</button>
                <button class="rightButton" onclick="deleteSeminar()">删除讨论课</button>
                <div class="clear"></div>
            </div>
            <div class="item">
                <button class="leftButton" id="grade">评分</button>
                <button class="leftButton" id="viewChoose">查看选题情况</button>
                <div class="clear"></div>
            </div>

        </div>
        <div class="itemBody" id="modifyInfo" style="display: none;">
            <div class="item">
                <label class="itemName">讨论课名称：</label>
                <input class="itemName" id="nameM">
            </div>
            <div class="item">
                <label class="itemName">讨论课说明:</label>
                <input class="itemName" id="descriptionM">
            </div>
            <div class="item">
                <label class="itemName">分组方式:</label>
                <select class="middleInput" id="groupingMethodM">
                    <option>固定分组</option>
                    <option>随机分组</option>
                </select>
            </div>
            <div class="item">
                <label class="itemName">开始时间</label>
                <input class="itemName" id="startTimeM">
            </div>
            <div class="item">
                <label class="itemName">结束时间</label>
                <input class="itemName" id="endTimeM"></input>
            </div>
                <div class="item">
                    <button class="middleButton" id="submitButton">保存</button>
                    <div class="clear"></div>
                </div>
        </div>
    </div>
    <div class="seminarInfo">
        <div class="title">Topic</div>
        <div class="line"></div>
        <div class="blockBody" id="seminarInfo">
                <div class="block" id="topicAdd">
                    <img class="addImg" src="/image/add.png" alt="添加">
                </div>
        </div>
    </div>
</div>
<script src="/js/teacher/seminarInfo.js"></script>
<script src="/js/teacher/modifySeminarInfo.js"></script>
<script src="/js/teacher/courseCommon.js"></script>
</@frame.page>