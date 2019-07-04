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
<script>
	$(document).ready(function () {
		var patha =$("#hidden").val();
		var arr = patha.split('%');
		for (var i = 0; i < arr.length-1; i++) {
			document.getElementById("name").value = arr[i];
			add();
		}
		document.getElementById("name").value = '';
	})


	var arr=[]
	function getVal() {
		var a=document.getElementById("name").value
		arr.push(a)
	}

	function test() {
		var processName = $("#processName").val();
		var id = $("#hiddenid").val();
		var path = " ";
		for (var i = 0; i < arr.length; i++) {
			path += arr[i]+"%";
		}
		$.ajax({
			url: "path/save",
			type: "post",
			async: true,
			contentType: 'application/json; charset=UTF-8',
			dataType: "json",
			data: JSON.stringify({"title": processName, "path": path,"id": id}),
			success: function(res){
				if (res.success) {
					window.location.href = "path/toindex";
				} else {
					alert(res);
				}
			},
			error: function(e){
				alert("网络异常，请稍后再试！");
			}
		});
	}
</script>
</head>
<body>
	<div>
		路线名称：
        <input type="text" id="processName" value="${path.title}"/>
        <input type="hidden" id="hidden" value="${path.path}"/>
        <input type="hidden" id="hiddenid" value="${path.id}"/>
		<br />
		<input value="" id="name" placeholder="点解流程节点可以删除" />
		<input type="button" id="adbten" value="增加" onclick="getVal();add()" />
		<input type="button" value="提交" onclick="test()"/>
	</div>
</body>
<script type="text/javascript">


</script>
</html>