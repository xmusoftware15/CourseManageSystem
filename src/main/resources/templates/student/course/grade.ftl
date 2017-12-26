<#import "*/frame.ftl" as frame/>
<@frame.page title="学生成绩">
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/content.css">
<div class="content">
    <div class="contentBlock">
        <div class="title">学生成绩</div>
        <div class="returnButton" id="returnButton">返回上一页</div>
        <div class="line"></div>
        <div class="itemBody">
            <table class="table table-striped">
                <thead class="tableTitle">
                <tr>
                    <th>讨论课</th>
                    <th>组名</th>
                    <th>组长</th>
                    <th>课堂讨论得分</th>
                    <th>报告分数</th>
                    <th>总分</th>
                </tr>
                </thead>
                <tbody class="tableContent">
                <#list data as grade>
                <tr>
                    <td>${grade.order}</td>
                    <td>${grade.groupName}</td>
                    <td>${grade.leader}</td>
                    <td>${grade.seminarGrade}</td>
                    <td>${grade.topicGrade}</td>
                    <td>${grade.score}</td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    $("#returnButton").click(function(){
        window.location.href = "/student/course/seminarInfo/fixed";
    })
</script>
</@frame.page>