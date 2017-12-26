<#import "*/frame.ftl" as frame/>
<@frame.page title="班级信息">
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/content.css">
<div class="content">
    <div class="contentBlock" id="showInfo">
        <div class="title" id="titleName"></div>
        <div class="returnButton" id="returnButton">返回上一页</div>
        <div class="line"></div>
        <div class="itemBody">
            <div class="item">
                <label class="itemName">班级名称:</label>
                <label class="itemName" id="className"></label>
            </div>
            <div class="item">
                <label class="itemName">上课地点:</label>
                <label class="itemName" id="classSite"></label>
            </div>
            <div class="item">
                <label class="itemName">上课时间:</label>
                <label class="itemName" id="classTime"> </label>
            </div>
            <div class="item">
                <label class="itemName">评分规则:</label>
                <label class="itemName">讨论课分数占比</label>
                <label class="itemName" id="classSeminar"> </label>
                <label class="itemName"> 报告分数占比</label>
                <label class="itemName" id="classReport"></label>
            </div>
            <div style="width: 80%;margin: 0 auto;">
                <table class="table table-striped" style="width: 80%;margin: 0 auto;">
                    <thead class="tableTitle">
                    <tr>
                        <th>分数</th>
                        <th>班级占比(%)</th>
                    </tr>
                    </thead>
                    <tbody class="tableContent" id="gradeMethon">

                    </tbody>
                </table>
            </div>
            <div class="item">
                <label class="itemName">学生名单:</label>
            </div>
            <div style="width: 80%;margin: 0 auto;">
                <table class="table table-striped" style="width: 80%;margin: 0 auto;">
                    <thead class="tableTitle">
                    <tr>
                        <th>学号</th>
                        <th>姓名</th>
                    </tr>
                    </thead>
                    <tbody class="tableContent">
                    <tr>
                        <td>24320152202000</td>
                        <td>sss</td>
                    </tr>
                    <tr>
                        <td>24320152202001</td>
                        <td>sss</td>
                    </tr>
                    <tr>
                        <td>24320152202002</td>
                        <td>xxx</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="item">
                <button class="submit" id="modifyButton">修改</button>
                <button class="reset" onclick="deleteClass()">删除班级</button>
                <div class="clear"></div>
            </div>
        </div>
    </div>
    <div class="contentBlock" id="modifyInfo" style="display: none;">
        <div class="title">修改班级</div>
        <div class="returnButton" id="returnButton">返回上一页</div>
        <div class="line"></div>
        <div class="itemBody">
            <div class="item">
                <label class="itemName">班级名称:</label>
                <input class="bigInput" id="classNameM">
            </div>
            <div class="item">
                <label class="itemName">上课地点:</label>
                <input class="bigInput" id="siteM">
            </div>
            <div class="item">
                <label class="itemName">上课时间:</label>
                <select class="bigSelect" id = "week">
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
                <input class="smallInput" placeholder="0-100" id="presentationM">
                <label class="itemName"> 报告分数占比</label>
                <input class="smallInput" placeholder="0-100" id="reportM">
            </div>
            <div class="item" style="margin-left: 25%">
                <label class="smallItemName">5分占比</label>
                <input type="text" name="seminarGrade" class="smallInput" placeholder="0-100" id="cM">
                <label class="smallItemName">4分占比</label>
                <input type="text" name="seminarGrade" class="smallInput" placeholder="0-100" id="bM">
                <label class="smallItemName">3分占比</label>
                <input type="text" name="seminarGrade" class="smallInput" placeholder="0-100" id="aM">
            </div>
            <div class="item">
                <label class="itemName">学生名单:</label>
            </div>
            <div style="width: 80%;margin: 0 auto;">
                <table class="table table-striped" style="width: 80%;margin: 0 auto;">
                    <thead class="tableTitle">
                    <tr>
                        <th>学号</th>
                        <th>姓名</th>
                    </tr>
                    </thead>
                    <tbody class="tableContent">
                    <tr>
                        <td>24320152202000</td>
                        <td>sss</td>
                    </tr>
                    <tr>
                        <td>24320152202001</td>
                        <td>sss</td>
                    </tr>
                    <tr>
                        <td>24320152202002</td>
                        <td>xxx</td>
                    </tr>
                    <tr>
                        <td id="addStudent">添加</td>
                    </tr>
                    </tbody>
                </table>
                <div class="item">
                    <button class="submit" id="submitButton"> 提交</button>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/js/teacher/courseCommon.js"></script>
<script src="/js/teacher/classInfo.js"></script>
<script src="/js/teacher/modifyClassInfo.js"></script>
</@frame.page>