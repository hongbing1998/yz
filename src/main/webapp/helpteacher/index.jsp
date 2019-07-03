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
<form class="form-inline definewidth m20" action="/apply/list" method="get">
    用户名称：
    <input type="text" name="name" id="param" class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
    <input type="submit" class="btn btn-primary" value="查询">
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>姓名</th>
        <th>报名标题</th>
		<th>操作</th>
    </tr>
    </thead>
	<c:forEach items="${allApply }" var="s">
		<tr>
			<td>${s.name }</td>
			<td>${s.employTitle}</td>
			<td>
				<a class="btn btn-success" href="/apply/apply/${s.id}">查看</a>
			</td>
		</tr>
	</c:forEach>
</body>
</html>
<script>
    $(function () {
		
    });

    function del(id)
	{
		if(confirm("确定要删除吗？"))
		{
			$.ajax({
				url:"manager/del/" + id,
				async: true,
				type: "post",
				success: function(result){
					alert(result);
					window.location.href = "manager/all";
				},
				error: function(){
					alert("netword is error");
				}
			});		
		}
	}
</script>