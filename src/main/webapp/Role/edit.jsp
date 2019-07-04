<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page deferredSyntaxAllowedAsLiteral="true"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath}/"/>
<title></title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="Css/style.css" />
<script type="text/javascript" src="Js/jquery.js"></script>
<script type="text/javascript" src="Js/bootstrap.js"></script>
<script type="text/javascript" src="Js/ckform.js"></script>
<script type="text/javascript" src="Js/common.js"></script>
<link href="assets/layui/css/layui.css" rel="stylesheet" />
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
<form action="/permissionRole/save" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover ">
        <tr>
            <td><input type="text" id="id" name="id" value="${role.id}" readonly="readonly"/></td>
        </tr>
        <tr>
            <td class="tableleft">角色名</td>
            <td>${role.roleName}</td>
        </tr>

        <tr>
            <td class="tableleft">角色</td>
            <td>
                <c:forEach items="${permissions}" var="permission">
                    <input id="permissionIds[]" type="checkbox" value="${permission.id}" name="permissionIds[]"
                           <c:if test="${permission.choice==true}">checked="checked"</c:if>>${permission.permissionName}
                </c:forEach>
            </td>
        </tr>


        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="submit" id="submit-button" class="btn btn-primary">保存</button>
                &nbsp;&nbsp;
                <button onclick="toAdd()" type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            </td>
        </tr>
    </table>
</form>
	</body>

</html>
<!--引入你自己的layui文件-->
<script src="assets/layui/layui.js"></script>
<!--xtree的js文件-->
<script src="assets/layui-xtree/layui-xtree.js"></script>
<script type="text/javascript">
    var toAdd = function () {
        window.location.href = "/role/list";
    }
</script>
