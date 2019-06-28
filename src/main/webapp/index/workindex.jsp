<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath %>">
    <meta charset="UTF-8">
    <title>西藏援藏系统</title>
    <link rel="stylesheet" href="Css/public.css">
    <link rel="stylesheet" href="Css/aidTibetJob.css">
</head>
<body>
<!--页头-->
<header>
    <div class="content">
        <!--logo-->
        <img src="Images/logo.png" alt="" class="logo">
        <!--导航-->
        <ul class="navLink">
            <li><a href="#">首页</a></li>
            <li><a href="#">援藏工作</a></li>
            <li><a href="#">援藏政策</a></li>
            <li><a href="#">援藏需求</a></li>
            <li><a href="#">援藏路径</a></li>
            <li><a href="#">援藏高校</a></li>
            <li><a href="#">工作动态</a></li>
            <li><a href="#">人员招聘</a></li>
        </ul>
        <!--登陆-->
		<a href="login.html" class="login">登陆</a>
    </div>
</header>
<!--内容-->
<div class="banner content">
    <img src="Images/twoPage_03.png" alt="">
</div>
<div class="content">
    <div class="inForBox">
        <div class="inForBox01">
            <ul class="pageMain">
                <li>
                    <a title="点击查看详情" href="#">
                        <span>测试标题</span>
                        <span>2018-04-20 08:00:00</span>
                    </a>
                </li>
                
            </ul>
            <div class="pageBox">
				<p class="len">共 <span>10</span>条记录</p>
				<p class="iNum">当前第 <span>1/2</span> 页</p>
               
				<a href='#' class="first">首页</a>
				<a href='#' class="prev">上一页</a>
				<a href='#' class="next">下一页</a>
				<a href='#' class="last">尾页</a>
			</div>
        </div>
    </div>
</div>
<!--页脚-->
<footer>
    <div class="content">
        <div class="footer-blue">
            <img src="Images/blue.png" alt="">
        </div>
        <div class="footerContent">
            <p>
                版权所有©西藏大学 藏ICP备05000064号 <a style="height:20px;line-height:20px;text-decoration:none;" target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=54010202000088"><img src="Images/beiantubiao.png">藏公网安备54010202000088号</a></p>
            <p>地址：西藏自治区拉萨市城关区藏大东路10号 邮政编码：850000 <img src="Images/pic1.gif" alt=""></p>
            <p>达内时代科技集团提供技术支持</p>
        </div>
        <div class="QrCode">
            <img src="Images/app.jpg" alt="">
        </div>
    </div>
</footer>
</body>
</html>