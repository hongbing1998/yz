<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
<head>
	<base href="${pageContext.request.contextPath}/"/>
    <title>援藏平台后台系统</title>
    <link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/main-min.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="header">

    <div class="dl-title">
        <!--<img src="/chinapost/Public/assets/img/top.png">-->
    </div>

    <div class="dl-log">欢迎您，<span class="dl-log-user">admin</span>
        <a href="/user/logout" id="logout" title="退出系统" class="dl-log-quit">[退出]</a>
        <a href="/main.jsp" id="main-button" title="回到首页" class="dl-log-quit">[首页]</a>
    </div>
</div>
<div class="content">
    <div class="dl-main-nav">
        <div class="dl-inform"><div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div></div>
        <ul id="J_Nav"  class="nav-list ks-clear">
            <li class="nav-item dl-selected">
                <div class="nav-item-inner nav-home">系统管理</div>
            </li>
        </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">

    </ul>
</div>
<script type="text/javascript" src="assets/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="assets/js/bui-min.js"></script>
<script type="text/javascript" src="assets/js/common/main-min.js"></script>
<script type="text/javascript" src="assets/js/config-min.js"></script>
<script>

    var config = [];
    $(document).ready(function () {
        $.ajax({
            url: "menu/list",
            type: "get",
            async: false,
            dataType: "json",
            success: function (res) {
                config.push(eval(res));
                console.log(config);
            },
        });
    })
    BUI.use('common/main',function(){

        new PageUtil.MainPage({
            modulesConfig : config
        });
    });
    
    
</script>
<div style="text-align:center;">
<p>来源：<a href="http://www.tedu.cn/" target="_blank">达内科技</a></p>
</div>
</body>
</html>