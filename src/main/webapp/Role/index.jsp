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
    <button type="button" class="btn btn-success" onclick="toAdd()" id="addnew">新增用户</button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>id</th>
        <th>角色名称</th>


        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${roles}" var="role">
        <tr>
            <td>${role.id}</td>
            <td>
                    ${role.roleName}
            </td>
            <td>
                <a class="option-button" href="/role/getRolePerssion?id=${role.id}">修改权限</a>
                |
                <button class="delete-button" id="delete-button" onclick="test('${role.id}')">删除</button>
                |<a class="option-button" href="/role/update?id=${role.id}">编辑</a>

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
</html>
<script>
    var toAdd = function () {
        window.location.href = "Role/add.jsp";
    }

    function test(id) {
        if (confirm("是否删除？")) {
            $.ajax({
                url: "role/" + id,
                type: "DELETE",
                async: false,
                dataType: "json",
                success: function (res) {
                    alert("删除成功！")
                    // window.location.href("policy/toPindex");
                    $.ajax({
                        url: "role/list",
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