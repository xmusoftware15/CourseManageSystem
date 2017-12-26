<#import "*/frame.ftl" as frame/>
<@frame.page title="讨论课信息">
<link rel="stylesheet" href="/css/content.css">
<link rel="stylesheet" href="/css/student/content.css">
<div class="content">
    <div>
        <div id="seminarName" class="title"></div>
        <div class="returnButton" id="returnButton">返回上一页</div>
        <div class="line"></div>
        <div class="itemBody">
            <div class="item">
                <label id="seminarDescription" class="itemName" style="width:100%"></label>
                <div class="lightline"></div>
            </div>
            <div class="item">
                <label class="itemName">分组方式：</label>
                <label id="seminarGroupingMethod" class="itemName"></label>
            </div>
            <div class="item">
                <label class="itemName">开始时间：</label>
                <label id="seminarStartTime" class="itemName"></label>
                <label class="itemName">结束时间：</label>
                <label id="seminarEndTime" class="itemName"></label>
            </div>
        </div>
        <#--<#if data.groupingMethod == "随机分组">-->
            <#--<div>-->
                <#--<div class="item">-->
                    <#--<div class="lightline"></div>-->
                <#--</div>-->
                <#--<div class="item">-->
                    <#--<label class="itemName">选择话题：</label>-->
                    <#--<label class="itemName">${group.topic.name}</label>-->
                <#--</div>-->
                <#--<div class="item">-->
                    <#--<label class="itemName">组号：</label>-->
                    <#--<label class="itemName">${group.id}</label>-->
                <#--</div>-->
                <#--<div class="item">-->
                    <#--<label class="itemName">组 长：</label>-->
                    <#--<label class="itemName">${group.leader.name}</label>-->
                <#--</div>-->
                <#--<div class="item">-->
                    <#--<label class="itemName">组 员：</label>-->
                    <#--<#list group.members as member>-->
                        <#--<label class="itemName">${member.name}</label>-->
                    <#--</#list>-->
                <#--</div>-->
            <#--</div>-->
        <#--</#if>-->
    </div>
    <div class="classInfo">
        <div class="title">话题</div>
        <div class="line"></div>
        <div class="blockBody" id="topicContainer">
            <#--<#list topic as topics>-->
                <#--<div class="block" onclick="topic('${topics.name}')">-->
                    <#--<div class="blockFont">${topics.name}</div>-->
                <#--</div>-->
            <#--</#list>-->
        </div>
        <div class="item">
            <link rel="stylesheet" href="/css/bootstrap.min.css">
            <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
            <script src="/js/bootstrap.min.js"></script>
            <button class="uploadbutton" data-toggle="modal" data-target="#myModal">上传报告</button>
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">上传报告</h4>
                        </div>
                        <div class="modal-body">
                            <div class="item">
                                <label>上传文件:</label>
                                <button class="selectFile">浏览</button>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary">提交</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>
        </div>
        <div class="item">
            <button class="viewscorebutton" id="grade">查看分数</button>
            <div class="clear"></div>
        </div>
    </div>
    <script src="/js/student/commonCourse.js"></script>
    <script src="/js/student/seminarInfo.js"></script>
</@frame.page>