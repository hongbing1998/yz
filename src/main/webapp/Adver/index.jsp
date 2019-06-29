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
	<form class="form-inline definewidth m20" action=""
		method="get">
		标题： <input type="text" name="param" id="param"
			class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>
		&nbsp;&nbsp;
		<button type="button" class="btn btn-success" id="addnew">新增需求</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>编号</th>
				<th>标题</th>
				<th>发布学校</th>
				<th>阅读量</th>
				<th>最后更新时间</th>
				<th>发布者</th>
				<th>操作</th>
			</tr>
		</thead>
		<tr>
			<td>1001</td>
			<td style="max-width: 220px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
				<a title="点击查看详情" href="#">测试标题</a>
			</td>
			<td style="max-width: 120px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
				北京大学
			</td>
			<td>0</td>
			<td>2018-04-20 08:00:00</td>
			<td>admin</td>
			<td>
				 <a class="option-button" href="#">编辑</a>
				|<a class="option-button" href="#">删除</a>
				|<a class="option-button" href="#">查看</a>
			</td>
		</tr>
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