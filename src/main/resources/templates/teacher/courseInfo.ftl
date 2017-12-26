<#import "*/teacherFrame.ftl" as frame/>
<@frame.page title="学生成绩">
<link rel="stylesheet" href="/css/content.css">
<link rel="stylesheet" href="/css/student/content.css">
<link rel="stylesheet" href="/css/bootstrap.min.css">

<div class="content">
    <div class="contentBlock">
        <div class="title">课程信息</div>
        <div class="line"></div>
        <div id="courseContainer">
        </div>
    </div>
</div>
<div class="clear"></div>
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/teacher/courseInfo.js"></script>
<script src="/js/teacher/addCourse.js"></script>
</@frame.page>