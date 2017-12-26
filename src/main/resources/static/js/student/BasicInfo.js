window.onload = getUserInfo();
function getUserInfo(){
    $.ajax({
        url: "/me" ,
        type: "GET",
        data:{
            "embedGrade" : false
        },
        headers : {'Authorization':'Bearer '+localStorage.getItem("jwt")},
        success: function (data) {
            console.log(data);
            $("#username").html(data.phone);
            $("#number").html(data.number);
            $("#name").html(data.name);
            if(data.gender == "male"){
                $("#gender").html("男");
            }
            else{
                $("#gender").html("女");
            }
            $("#school").html(data.school.name);
            if(data.education == 1){
                $("#title").html("本科生");
            }
            else if(data.education == 2){
                $("#title").html("研究生");
            }
            else{
                $("#title").html("博士生");
            }

            $("#email").html(data.email);
        }
    });
}
var showNode = $("#showInfo");
var modifyNode = $("#modifyInfo");

function modifyOnclick() {
    $("label#usernameM").html($("label#username").text());
    $("input#number").val($("label#number").text());
    $("input#name").val($("label#name").text());
    $("input#gender").val($("label#gender").text());
    $("input#school").val($("label#school").text());
    $("input#title").val($("label#title").text());
    $("input#email").val($("label#email").text());
    console.log("修改完成！");

    showNode.toggle();
    modifyNode.toggle();
}

function submitOnclick() {
    console.log("提交修改信息");
    var gender;
    console.log($("select#gender").find("option:selected").text());
    if($("select#gender").find("option:selected").text() == "男"){
        gender = "male";
    } else{
        gender = "female";
    }
    userInfo = {
        username: $("label#username").text(),
        number: $("input#number").val(),
        name: $("input#name").val(),
        gender: gender,
        school: $("input#school").val(),
        title: $("input#title").val(),
        email : $("input#email").val()
    };

    $.ajax({
        url: "/me" ,
        type: "PUT",
        headers : {'Authorization':'Bearer '+localStorage.getItem("jwt")},
        data:JSON.stringify(userInfo),
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            $("label#number").text(userInfo.number);
            $("label#name").text(userInfo.name);
            if(userInfo.gender == "male"){
                $("label#gender").html("男");
            }else {
                $("label#gender").html("女");
            }
            $("label#school").text(userInfo.school);
            $("label#title").text(userInfo.title);
            $("label#email").text(userInfo.email);
            showNode.toggle();
            modifyNode.toggle();
            alert("修改成功!");
        }
    });
}

$("#submitButton").click(submitOnclick);
$("#modifyButton").click(modifyOnclick);