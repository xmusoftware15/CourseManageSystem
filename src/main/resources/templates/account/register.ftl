<#import "*/accountFrame.ftl" as frame/>
<@frame.page title="注册">
<div class="phoneNum">手机号:<input type="text" name="phoneNum" id="phoneNum"/></div>
<div class="password">密码:<input type="text" name="password" id="password"/></div>
<div class="name">姓名:<input type="text" name="name" id="name"/></div>
<div class="province" >省:<select class="provinceSelect" id="province"></select></div>
<div class="city">市:<select class="citySelect" id="city"></select></div>
<div class="school">学校:<select class="schoolSelect" id="school"></select>
    <div class="createSchool" id="createSchool">创建学校</div>
</div>
<div class="gender">性别:</div>
<div class="male"><input type="radio" value="男" name="gender" checked="checked" />男</div>
<div class="female"><input type="radio" value="女" name="gender"/>女</div>
<div class="role">角色:</div>
<div class="student"><input type="radio" value="学生" name="role" checked="checked"/>学生</div>
<div class="teacher"><input type="radio" value="教师" name="role"/>教师</div>
<div class="stuffNum" id="stuffNum">教工号:<input type="text" name="stuffNum"/></div>
<div class="eMail" id="email">邮箱:<input type="text" name="eMail"/></div>
<div class="submit" id="submit">提交</div>
<script src="/js/jquery-1.7.2.min.js"></script>
<script src="/js/jquery.easing.min.js"></script>
<script src="/js/account/register.js"></script>
</@frame.page>