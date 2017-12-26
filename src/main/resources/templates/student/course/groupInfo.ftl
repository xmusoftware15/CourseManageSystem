<#import "*/frame.ftl" as frame/>
<@frame.page title="教师评分">
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/content.css">
<div class="content">
    <div class="contentBlock" id="showInfo">
        <div class="title">分组信息</div>
        <div class="returnButton" id="returnButton">返回上一页</div>
        <div class="line"></div>
        <div class="itemBody"">
            <table class="table table-striped">
                <thead class="tableTitle">

                <tr>
                    <th>角色</th>
                    <th>学号</th>
                    <th>姓名</th>
                </tr>
                </thead>
                <tbody class="tableContent">
                <tr>
                    <td>队长</td>
                    <td>${group.leader.number}</td>
                    <td>${group.leader.name}</td>
                </tr>
                    <#list group.members as member>
                    <tr>
                        <td>队员</td>
                        <td>${member.number}</td>
                        <td>${member.name}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
            <div class="item">
                <button class="submit" id="modifyButton">修改</button>
            </div>
        </div>
    </div>
    <div class="contentBlock" id="modifyInfo" style="display: none;">
        <div class="title">分组信息</div>
        <div class="returnButton" id="returnButton">返回上一页</div>
        <div class="line"></div>
        <div class="itemBody"  style="height: auto!important;">
            <table class="table table-striped">
                <thead class="tableTitle">

                <tr>
                    <th>角色</th>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody class="tableContent" id="deleteTable">
                <tr>
                    <td>队长</td>
                    <td>${group.leader.number}</td>
                    <td>${group.leader.name}</td>
                </tr>
                    <#list group.members as member>
                    <tr>
                        <td>队员</td>
                        <td>${member.number}</td>
                        <td>${member.name}</td>
                        <td class="deleteMember">删除</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
        <div class="title">添加成员</div>
        <div class="line"></div>
        <div class="itemBody" style="height: auto!important;">
            <table class="table table-striped">
                <thead class="tableTitle">

                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody class="tableContent" id="addTable">
                    <tr>
                        <td>2154846512312</td>
                        <td>tomcat</td>
                        <td class="addMember">添加</td>
                    </tr>
                    <tr>
                        <td>sdasdaa12312</td>
                        <td>spring</td>
                        <td class="addMember">添加</td>
                    </tr>
                    <tr>
                        <td>s16815122312</td>
                        <td>idea</td>
                        <td class="addMember">添加</td>
                    </tr>
                    <tr>
                        <td>s1618632312</td>
                        <td>eclipse</td>
                        <td class="addMember">添加</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="item">
            <button class="submit" id="submitButton">保存</button>
        </div>
    </div>
</div>
<script>
    $("#returnButton").click(function(){
        window.location.href = "/student/course/home/OOAD";
    })
</script>
<script src="/js/student/modifyGroupInfo.js"></script>
</@frame.page>