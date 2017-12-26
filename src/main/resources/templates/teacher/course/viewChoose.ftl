<#import "*/frame.ftl" as frame/>
<@frame.page title="查看话题选择情况">
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/content.css">
<div class="content">
    <div class="contentBlock">
        <div class="title">选题情况</div>
        <div class="returnButton" id="returnButtton">返回上一页</div>
        <div class="line"></div>
        <div class="itemBody">
            <table class="table table-striped">
                <thead class="tableTitle">
                <tr>
                    <th>Topic</th>
                    <th>组名</th>
                </tr>
                </thead>
                <tbody class="tableContent">
                <tr>
                    <td>A1</td>
                    <td>Bangalore</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="/js/teacher/courseCommon.js"></script>
</@frame.page>