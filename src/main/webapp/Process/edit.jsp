<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>援藏路线修改</title>
<base href="${pageContext.request.contextPath }/"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="./Process/style.css" />
<script type="text/javascript" src="./Process/create.js"></script>
<script type="text/javascript" src="./Js/jquery.js"></script>
</head>
<body>
	<div>
		路线名称：
        <input type="text" id="processName" value="${path.title}"/>
		<br />
		<input value="" id="name" placeholder="点解流程节点可以删除" />
		<input type="button" value="增加" onclick="add();" />
		<input type="button" value="提交"/>
	</div>
</body>
<script type="text/javascript">

	
</script>
</html>