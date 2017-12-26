<#import "*/infoFrame.ftl" as frame/>
<@frame.page title="学生成绩">
<link rel="stylesheet" href="/css/content.css">
<link rel="stylesheet" href="/css/student/content.css">
<div class="content">
    <div class="contentBlock">
        <div class="title">课程信息</div>
        <div class="line"></div>
        <div class="item">
            <label class="itemName">课程名称：</label>
            <input class="middleInput" id="courseName">
        </div>
        <div class="item">
            <div class="itemName">任课教师：</div>
            <input class="middleInput" id="re">
            <button class="rightButton" id="checkButton" style="margin-top: 0;">查询</button>
        </div>
        <div id="courseContainer">
            <#--<#list classes as classes>-->
                <#--<div class="main_box_right_content" id="${classes.name}">-->
                    <#--<h3 class="classtitle"><span id="course">${classes.course.name}</span>-->
                        <#--<button class="main_box_right_content_button" onclick="selectCourses('${classes.name}')">选择课程</button>-->
                    <#--</h3>-->
                    <#--<div class="divideline"></div>-->
                    <#--<div class="classinfo" style="margin: 0 auto;text-align:justify">-->
                        <#--<div class="item">-->
                            <#--<label class="itemName">班级:</label>-->
                            <#--<label class="itemName">${classes.name}</label>-->
                            <#--<label class="itemName" style="margin-left: 30%;">课程地点：</label>-->
                            <#--<label class="itemName">${classes.site}</label>-->
                        <#--</div>-->
                        <#--<div class="item">-->
                            <#--<label class="itemName">教师:</label>-->
                            <#--<label class="itemName">${classes.teacher}</label>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</#list>-->
        </div>
    </div>
</div>
<div class="clear"></div>
<script src="/js/student/chooseCourse.js"></script>
</@frame.page>