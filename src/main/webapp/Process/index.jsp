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
	<script type="text/javascript">
		function test(id){

			if(confirm("是否删除？")){
				$.ajax({
					url: "path/"+id,
					type: "DELETE",
					async: false,
					success: function (res) {
						alert("删除成功！")
						$.ajax({
							url: "/path/toindex",
							type: "get",
							async: false,
							success: function (res) {
								window.location.reload();
							}
						});
					}
				});
			}
		}

	</script>
</head>
<body>
<form  action="/path/search" method="post">
	标题： <input type="text" name="title" id="param"
			   class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
	<button type="submit" class="btn btn-primary">查询</button>
	&nbsp;&nbsp;
	<a href="Process/add.jsp"><button type="button" class="btn btn-success" id="addnew" >发布路线</button></a>
</form>
<table class="table table-bordered table-hover definewidth m10">
	<thead>
	<tr>
		<th>编号</th>
		<th>标题</th>
		<th>最后更新时间</th>
		<th>发布者</th>
		<th>操作</th>
	</tr>
	</thead>

	<c:forEach items="${resultObj.data}" var="data">
		<tr>
			<td>${data.id}</td>
			<td style="max-width: 260px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
				${data.title}
			</td>
			<td>${data.createDate}</td>
			<td>${data.userName}</td>
			<td>
				<a class="option-button" href="/path/toedit/${data.id}">编辑与查看</a>
				|<button class="delete-button" id ="delete-button" onclick="test('${data.id}')">删除</button>
			</td>
		</tr>
	</c:forEach>
	<c:forEach items="${paths}" var="data">
		<tr>
			<td>${data.id}</td>
			<td style="max-width: 260px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
				${data.title}
			</td>
			<td>${data.createDate}</td>
			<td>${data.userName}</td>
			<td>
				<a class="option-button" href="/path/toedit/${data.id}">编辑与查看</a>
				|<button class="delete-button" id ="delete-buttonb" onclick="test('${data.id}')">删除</button>
			</td>
		</tr>
	</c:forEach>

</table>
<div class="inline pull-right page">
	10 条记录 1/2页
	<a href='#'>首页</a>
	<a href='#'>上一页</a>
	<a href='#'>下一页</a>
	<a href='#'>尾页</a>
</div>
</body>
<script>
</script>
</html>