<#import "*/infoFrame.ftl" as frame/>
<@frame.page title="学生成绩">
<link rel="stylesheet" href="/css/content.css">
<div class="content">
    <div class="contentBlock">
        <div class="title">基本信息</div>
        <div class="line"></div>
        <div class="itemBody">
            <div class="imgarea"><img src="/image/user.png"/></div>
            <div class="info" id="showInfo">
                <div class="item">
                    <label class="itemName">用户名: </label>
                    <label class="itemName" id="username"></label>
                    <label class="itemName" style="margin-left: 20%;">学号: </label>
                    <label class="itemName" id="number"></label>
                </div>
                <div class="item">
                    <label class="itemName">姓名: </label>
                    <label class="itemName" id="name"></label>
                    <label class="itemName" style="margin-left: 20%;">性别: </label>
                    <label class="itemName" id="gender"></label>
                </div>
                <div class="item">
                    <label class="itemName">学校: </label>
                    <label class="itemName" id="school"></label>
                    <label class="itemName" style="margin-left: 20%;">学历: </label>
                    <label class="itemName" id="title"></label>
                </div>
                <div class="item">
                    <label class="itemName">E-mail: </label>
                    <label class="itemName" id="email"></label>
                </div>
                <div class="item">
                    <button class="middleButton" id="modifyButton">修 改</button>
                </div>
            </div>
            <div class="info" id="modifyInfo" style="display: none;">
                <div class="item">
                    <label class="itemName">用户名: </label>
                    <label class="itemName" id="usernameM"></label>
                    <label class="itemName" style="margin-left: 20%;">学号: </label>
                    <input class="middleInput" id="number">
                </div>
                <div class="item">
                    <label class="itemName">姓名: </label>
                    <input class="middlerInput" id="name">
                    <label class="itemName" style="margin-left: 20%;">性别: </label>
                    <select class="smallSelect" id="gender">
                        <option>男</option>
                        <option>女</option>
                    </select>
                </div>
                <div class="item">
                    <label class="itemName">学校: </label>
                    <input class="middlerInput" id="school">
                    <label class="itemName" style="margin-left: 20%;">学历: </label>
                    <input class="middlerInput" id="title">
                </div>
                <div class="item">
                    <label class="itemName">E-mail: </label>
                    <input class="middleInput" id="email">
                </div>
                <div class="item">
                    <button class="middleButton" id="submitButton">保 存</button>
                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<div class="clear"></div>
<script src="/js/student/basicInfo.js"></script>
</@frame.page>