<#import "*/frame.ftl" as frame/>
<@frame.page title="教师课程主页">
<div class="content">
    <div class="classInfo">
        <div class="title">课程班级</div>
        <div class="returnButton" id="returnButton">返回上一页</div>
        <div class="line"></div>
        <div class="blockBody" id="blockBody">
            <div class="block" id="classAdd">
                <img class="addImg" src="/image/add.png" alt="添加">
            </div>
        </div>
    </div>
    <div class="seminarInfo">
        <div class="title">讨论课</div>
        <div class="line"></div>
        <div class="blockBody" id="seminarInfo">
            <div class="block" id="seminarAdd">
                <img class="addImg" src="/image/add.png" alt="添加">
            </div>
        </div>
    </div>
</div>
<script src="/js/teacher/CourseHome.js"></script>
<script src="/js/teacher/courseCommon.js"></script>
</@frame.page>