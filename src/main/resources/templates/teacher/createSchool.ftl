<#import "*/accountFrame.ftl" as frame/>
<@frame.page title="注册">
<div class="province" >省:<select class="provinceSelect" id="province"></select></div>
<div class="city">市:<select class="citySelect" id="city"></select></div>
<div class="school">学校:<input class="schoolSelect" id="school"></div>
<div class="submit" id="submit">提交</div>
<script src="/js/jquery-1.7.2.min.js"></script>
<script src="/js/jquery.easing.min.js"></script>
<script src="/js/teacher/createSchool.js"></script>
</@frame.page>