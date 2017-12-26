<#import "*/frame.ftl" as frame/>
<@frame.page title="学生课程主页">
<link rel="stylesheet" href="/css/content.css">
<div class="content">
    <div class="classInfo">
        <div class="title">讨论课</div>
        <div class="returnButton" id="returnButton">返回上一页</div>
        <div class="line"></div>
        <div class="blockBody" id="seminarContainer">
            <#--<#if name == "OOAD">-->
                <#--<#list data as seminarName>-->
                    <#--<div class="block" onclick="seminar('${seminarName}')">-->
                        <#--<div class="blockFont">${seminarName}</div>-->
                    <#--</div>-->
                <#--</#list>-->
            <#--</#if>-->
        </div>
    </div>
    <div class="seminarInfo">
        <div class="title">课程分组</div>
        <div class="line"></div>
        <div class="blockBody">
            <div class="block">
                <div class="blockFont" id="group">固定分组</div>
            </div>
        </div>
    </div>
</div>
<script src="/js/student/commonCourse.js"></script>
<script src="/js/student/courseHome.js"></script>
</@frame.page>