<#import "*/frame.ftl" as frame/>
<@frame.page title="创建班级">
<link rel="stylesheet" href="/css/content.css">
<div class="content">
    <div class="contentBlock">
        <div class="title">创建班级</div>
        <div class="returnButton" id="returnButton">返回上一页</div>
        <div class="line"></div>
        <div class="itemBody">
            <div class="item">
                <label class="itemName">班级名称:</label>
                <input class="bigInput" id="name">
            </div>
            <div class="item">
                <label class="itemName">上课地点:</label>
                <input class="bigInput" id="site">
            </div>
            <div class="item">
                <label class="itemName">上课时间:</label>
                <select class="bigSelect" id="week">
                    <option>单周</option>
                    <option>双周</option>
                </select>
                <select class="smallSelect" id="day">
                    <option>周一</option>
                    <option>周二</option>
                    <option>周三</option>
                    <option>周四</option>
                    <option>周五</option>
                </select>
                <select class="smallSelect" id="detail">
                    <option>1-2节</option>
                    <option>3-4节</option>
                    <option>5-6节</option>
                    <option>7-8节</option>
                </select>
                <button class="addButton">添加</button>
            </div>
            <div class="item">
                <label class="itemName">评分规则:</label>
                <label class="itemName">讨论课分数占比</label>
                <input class="smallInput" placeholder="0-100" id="presentation">
                <label class="itemName"> 报告分数占比</label>
                <input class="smallInput" placeholder="0-100" id="report">
            </div>
            <div class="item" style="margin-left: 25%">
                <label class="smallItemName">5分占比</label>
                <input type="text" name="seminarGrade" class="smallInput" placeholder="0-100" id="c">
                <label class="smallItemName">4分占比</label>
                <input type="text" name="seminarGrade" class="smallInput" placeholder="0-100" id="b">
                <label class="smallItemName">3分占比</label>
                <input type="text" name="seminarGrade" class="smallInput" placeholder="0-100" id="a">
            </div>
            <div class="item">
                <label class="itemName">导入学生名单:</label>
                <input name="file" type="file" value="浏览" id="studentFile">
                <button class="selectFile" id="uploadFile">上传文件</button>
            </div>
            <div class="item">
                <button class="submit" id="submitClass">提交</button>
                <button class="reset">重置</button>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
<script src="/js/teacher/createClass.js"></script>
<script src="/js/teacher/courseCommon.js"></script>
</@frame.page>