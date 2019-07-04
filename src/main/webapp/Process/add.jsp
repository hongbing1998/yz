<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>援藏路线新增</title>
<base href="${pageContext.request.contextPath }/"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="./Process/style.css" />
<script type="text/javascript" src="./Process/create.js"></script>
<script type="text/javascript" src="./Js/jquery.js"></script>
	<script type="text/javascript">
		var arr=[]
		function getVal() {
			var a=document.getElementById("name").value
			arr.push(a)
		}

		function test() {
			var processName = $("#processName").val();
			var path = " ";
			for (var i = 0; i < arr.length; i++) {
				path += arr[i]+"%";
			}
			if(processName == null || processName == ""){
				alert("标题不能为空");
				return;
			}
			if(path == null || path == " "){
				alert("路径不能为空");
				return;
			}
			$.ajax({
				url: "path/save",
				type: "post",
				async: true,
				contentType: 'application/json; charset=UTF-8',
				dataType: "json",
				data: JSON.stringify({"title": processName, "path": path}),
				success: function(res){
					if (res.success) {
						window.location.href = "/path/page_query/1/5";
					} else {
						alert(res);
					}
				},
				error: function(e){
					alert("网络异常，请稍后再试！");
				}
			});
		}

		function isEmpty() {
			var name = document.getElementById("name").value;
			if(name == null || name == ""   ){
				alert("不能添加空节点");
				return false;
			}
			return true;
		}
		function isEmpty2() {
			var name = document.getElementById("name").value;
			if(name == null || name == ""   ){
				return false;
			}
			return true;
		}
	</script>
</head>
<body>
	<div>
			路线名称：
			<input type="text" id="processName" value=""/>
			<br />
			<input id="name" placeholder="点解流程节点可以删除" />
			<input type="hidden" id="hidden" value="${path.path}"/>
			<input type="button" value="增加" onclick="if (isEmpty()) add();if (isEmpty2()) getVal()" />
			<input type="button" value="提交" onclick="test()"/>
		<a href="/path/page_query/1/5">

			<input type="button" value="返回列表"/>
		</a>
	</div>
</body>

</html>