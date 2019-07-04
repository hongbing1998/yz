<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
<link rel="stylesheet" type="text/css" href="./login/css/style.css" />
<link rel="stylesheet" type="text/css" href="./login/css/skin_/login.css" />
<script type="text/javascript" src="./login/js/jquery.js"></script>
<script type="text/javascript" src="./login/js/jquery.select.js"></script>
<title>援藏平台后台管理系统_用户登录</title>
</head>

<body>
<div id="container">
    <div id="bd">
    	<div id="main">
        	<div class="login-box">
                <div id="logo"></div>
                <h1></h1>
                <div class="input username" id="username">
                    <label for="userName">用户名</label>
                    <input type="text" id="account"/>
                </div>
                <div class="input psw" id="psw">
                    <label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                    <input type="password" id="passWord" />
                </div>


                <div id="btn" class="loginButton" style="margin-left: -264px;">
                    <label>记住我</label>
                    <input type="checkbox" id="remeberMe" value="true"/>
                    <input type="button" id="valiDate" class="button" value="登录"  />
                </div>
            </div>
        <div id="ft">CopyRight&nbsp;2018&nbsp;&nbsp;版权所有&nbsp;&nbsp;More Templates <a href="#" target="_blank" title="达内科技">达内科技</a></div>
        </div>
    </div>
   <textarea rows="5" cols="5"></textarea>
</div>

</body>
<script type="text/javascript">
	var height = $(window).height() > 445 ? $(window).height() : 445;
	$("#container").height(height);
	var bdheight = ($(window).height() - $('#bd').height()) / 2 - 20;
	$('#bd').css('padding-top', bdheight);
	$(window).resize(function(e) {
        var height = $(window).height() > 445 ? $(window).height() : 445;
		$("#container").height(height);
		var bdheight = ($(window).height() - $('#bd').height()) / 2 - 20;
		$('#bd').css('padding-top', bdheight);
    });
	$('select').select();

	$("#valiDate").click(function(){
        var account = $("#account").val();
		var password = $("#passWord").val();
        var remeberMe = $("#remeberMe").val();
        if (account == null || account == "") {
            alert("账户不能为空");
            return;
        }
        if (password == null || password == "") {
            alert("密码不能为空");
            return;
        }

		$.ajax({
			url: "user/login",
			type: "post",
			async: true,
            contentType: 'application/json; charset=UTF-8',
            dataType: "json",
            data: JSON.stringify({"account": account, "password": password, "remeberMe": remeberMe}),
			success: function(res){
                if (res.success) {
                    window.location.href = "index.jsp";
				} else {
                    alert("用户名密码错误");
				}
			},
			error: function(e){
				alert("网络异常，请稍后再试！");
			}
		});

	});

</script>

</html>
