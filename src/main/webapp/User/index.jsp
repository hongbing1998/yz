<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page deferredSyntaxAllowedAsLiteral="true"%>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <base href="${pageContext.request.contextPath}/" />
    <link rel="stylesheet" type="text/css" href="./Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="./Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="./Css/style.css" />
    <script type="text/javascript" src="./Js/jquery.js"></script>
    <script type="text/javascript" src="./Js/bootstrap.js"></script>
    <script type="text/javascript" src="./Js/ckform.js"></script>
    <script type="text/javascript" src="./Js/common.js"></script>

 

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }
		.option-button:hover {
			color:red;
		}

    </style>
</head>
<body>
<form class="form-inline definewidth m20" action="manager/search" method="get">    
    用户名称：
    <input type="text" name="param" id="param" class="abc input-default" placeholder="" value="">&nbsp;&nbsp;  
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp;
    <button type="button" class="btn btn-success" onclick="toAdd()" id="addnew">新增用户</button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>管理员id</th>
        <th>管理员名称</th>
        <th>所属学校</th>
        <th>电话</th>
        <th>级别</th>
        <th>创建时间</th>
        <th>其它</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${users }" var="user">
    	<tr>
			<td>${user.id }</td>
			<td>
				<a title="点击查看详情" href="#">
					${user.username }
				</a>
			</td>
			<td>${user.schoolname }</td>
			<td>${user.telephone }</td>
			<td>${user.level }</td>
			<td>${user.createtime }</td>
			<td>${user.other }</td>
			<td>
				 <a class="option-button" href="#">编辑</a>
				|<a class="option-button" href="#">删除</a>
				|<a class="option-button" href="#">查看</a>
			</td>
		</tr>
    </c:forEach>
		
</table>
	<div class="inline pull-right page">
		${page.count }条记录 ${page.index }/${page.totalPage }页 
		<a href='user/list'>首页</a>
		
		<c:choose>
			<c:when test="${page.index - 1 > 0 }">
				<a href='user/list?index=${page.index - 1 }'>上一页</a>
			</c:when>
			<c:otherwise>
				上一页
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${page.index + 1 > page.totalPage }">
				下一页
			</c:when>
			<c:otherwise>
				<a href='user/list?index=${page.index + 1 }'>下一页</a>
			</c:otherwise>
		</c:choose>
		<a href='user/list?index=${page.totalPage }'>尾页</a>
	</div>
</body>
</html>
<script>
   var toAdd = function(){
	   window.location.href = "user/toadd";
   }
</script>