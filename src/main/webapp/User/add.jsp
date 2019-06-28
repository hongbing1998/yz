<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page deferredSyntaxAllowedAsLiteral="true"%>

<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath}/" />
<title></title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="./Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="./Css/style.css" />
<script type="text/javascript" src="./Js/jquery.js"></script>
<script type="text/javascript" src="./Js/bootstrap.js"></script>
<script type="text/javascript" src="./Js/ckform.js"></script>
<script type="text/javascript" src="./Js/common.js"></script>
<script type="text/javascript" charset="utf-8"
	src="./ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="./ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="./ueditor/lang/zh-cn/zh-cn.js"></script>

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
</style>
</head>
<body>
	<form action="user/add" method="post" class="definewidth m20">
		<table class="table table-bordered table-hover ">
			<tr>
				<td class="tableleft">管理员名称</td>
				<td><input type="text" id="userName" name="username"/></td>
			</tr>
			<tr>
				<td class="tableleft">管理员密码</td>
				<td><input type="text" id="passWord" name="password"/></td>
			</tr>
			<tr>
				<td class="tableleft">所属学校</td>
				<td>
					<select id="schId" name="schoolid">
						<c:forEach items="${schools }" var="school"> 
							<option value="${school.id }">${school.schoolname }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="tableleft">电话</td>
				<td><input type="text" id="telephone" name="telephone"/></td>
			</tr>
			<tr>
				<td class="tableleft">级别</td>
				<td><select id="roleId" name="level">
						<option value="1" selected="selected">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
				</select></td>
			</tr>
			<tr>
				<td class="tableleft"></td>
				<td>
					<button type="submit" id="submit-button" class="btn btn-primary">保存</button> &nbsp;&nbsp;
					<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
				</td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
	
</script>
</html>