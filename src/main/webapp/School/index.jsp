<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page deferredSyntaxAllowedAsLiteral="true"%>

<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath}/" />
<title></title>
<meta charset="UTF-8">
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

@media ( max-width : 980px) {
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
<form class="form-inline definewidth m20" action="/school/condition_query" method="get">
	学校名称： <input type="text" name="param" id="param" class="abc input-default" placeholder="输入要查询的学校名称"
				 value="${schoolName}">&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>
	<button type="button" class="btn btn-success" onclick="window.location.href='School/add.jsp'" id="addnew">新增学校
	</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>学校编号</th>
				<th>学校名称</th>
				<th>需要援助</th>
				<th>前往援助</th>
				<th>管理操作</th>
			</tr>
		</thead>
		<tr>
			<td>${school.id}</td>
			<td style="max-width: 220px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
				<a title="点击查看详情" href="/school/show/${school.id}">${school.schoolName}</a>
			</td>
			<td>是</td>
			<td>否</td>
			<td>
				<a class="option-button" href="/school/edit/${school.id}">编辑</a>
				<a class="option-button" href="/school/delete/${school.id}">删除</a>
				<a class="option-button" href="/school/show/${school.id}">查看</a>
			</td>
		</tr>
	</table>
	<div class="inline pull-right page">
		${page.total}条记录 ${page.current}/${page.pages}页
		<a href='/school/page_query/1/${page.size}'>首页 |</a>
		<c:if test="${page.hasPrevious()}">
			<a href='/school/page_query/${page.current - 1}/${page.size}'>上一页 |</a>
		</c:if>
		<c:if test="${not page.hasPrevious()}">
			上一页 |
		</c:if>
		<c:if test="${page.hasNext()}">
			<a href='/school/page_query/${page.current + 1}/${page.size}'>下一页 |</a>
		</c:if>
		<c:if test="${not page.hasNext()}">
			下一页 |
		</c:if>
		<a href='/school/page_query/${page.pages}/${page.size}'>尾页</a>
	</div>
</body>
<script>
    var toAdd = function () {
        window.location.href = "School/add.jsp";
    }
</script>
</html>