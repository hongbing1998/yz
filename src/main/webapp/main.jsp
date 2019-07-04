<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath %>">
    <title>西藏援藏系统</title>
    <link rel="stylesheet" href="index/css/public.css">
    <link rel="stylesheet" href="index/css/index.css">
</head>
<body>
<!--网页头部-->
<header>
    <div class="content">
        <!--logo-->
        <img src="image/logo.png" alt="" class="logo">
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
        <a href="/user/toLogin" class="login">登录</a>
    </div>
</header>
<!--网页内容-->
<section id="app">
    <div class="content">
        <div class="section01">
            <ul class="section01-left">
                <li>
                    <div class="sectionMode01">
                        <span></span>
                        <h1>援藏工作</h1>
                    </div>
                    <div class="sectionMode02">
                        <a href="">查看更多</a>
                    </div>
                </li>
                <li>
                    <div class="sectionMode01">
                        <span></span>
                        <h1>援藏政策</h1>
                    </div>
                    <div class="sectionMode02">
                        <a href="">查看更多</a>
                    </div>
                </li>
                <li>
                    <div class="sectionMode01">
                        <span></span>
                        <h1>援藏路径</h1>
                    </div>
                    <div class="sectionMode02">
                        <a href="">查看更多</a>
                    </div>
                </li>
                <li>
                    <div class="sectionMode01">
                        <span></span>
                        <h1>人员招聘</h1>
                    </div>
                    <div class="sectionMode02">
                        <a href="">查看更多</a>
                    </div>
                </li>
                <li>
                    <div class="sectionMode01">
                        <span></span>
                        <h1>援藏需求</h1>
                    </div>
                    <div class="sectionMode02">
                        <a href="">查看更多</a>
                    </div>
                </li>
                <li>
                    <div class="sectionMode01">
                        <span></span>
                        <h1>援藏高校</h1>
                    </div>
                    <div class="sectionMode02">
                        <a href="">查看更多</a>
                    </div>
                </li>
            </ul>
            <div class="section01-right">
                <div class="section01-right-title">
                    <span></span>
                    人员招聘
                </div>
                <ul class="list">
                    <li v-for="(value,index) in employment" style="color: #0C0C0C"><a href="display01.jsp" style="color: #0C0C0C">{{value.content}}</a></li>
                </ul>
            </div>
        </div>
        <div class="section02">
            <div class="section02-left">
                <div class="section02-left-img">
                    <ul class="section02-slider-img">
                        <li><img src="image/img03.jpg" alt="1"></li>
                        <li><img src="image/img01.jpg" alt="2"></li>
                        <li><img src="image/img02.jpg" alt="3"></li>
                    </ul>
                    <div class="section02-slider-btn">
                        <span index="1" class="on"></span>
                        <span index="2"></span>
                        <span index="3"></span>
                    </div>
                </div>
                <h4>援藏老师和学生户外项目</h4>
            </div>
            <div class="section02-center">
                <div class="section02-center-title">
                    <h1>援藏需求</h1>
                    <a href="">更多</a>
                </div>
                <ul class="list">
                    <li v-for="(value,index) in demand" style="color: #0C0C0C"><a href="display02.jsp" style="color: #0C0C0C">{{value.title}}</a></li>
                </ul>
            </div>
            <div class="section02-right">
                <div class="section02-right-title">
                    <span></span>
                    常见问题
                </div>
                <ul class="list">
                    <li v-for="(value,index) in employment" style="color: #0C0C0C"><a href="display01.jsp" style="color: #0C0C0C">{{value.title}}</a></li>
                </ul>
            </div>
        </div>
    </div>
</section>
<!--网页底部-->
<footer>
    <div></div>
</footer>
<script src="index/js/jquery-3.3.1.min.js"></script>
<script src="index/js/vue.js"></script>
<script type="text/javascript">
    new Vue({
        el:'#app',
        data:{
            employment:[],
            demand:[]
        },
        methods:{
        },
        created: function employ(){
            $.ajax({
                url:'/employment/list',
                type:'get',
                dataType:'json',
                data:'',
                success:(result)=>{
                if(result.success){
                // console.log(result.resultObj)
                this.employment=result.resultObj;
            }
        },
        });
            $.ajax({
                url:'/demand/list',
                type:'get',
                dataType:'json',
                data:'',
                success:(result)=>{
                if(result.success){
                // console.log(result.resultObj)
                this.demand=result.resultObj;
            }
        },
        });
        }
    })
</script>
</body>
</html>