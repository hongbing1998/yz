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
<form action="/user/save" method="post">
    <table class="table table-bordered table-hover definewidth m10">
        <<input style="display: none" id="id" name="id" value="${user.id}"></input>
        <tr>
            <td class="tableleft">管理员名称</td>
            <td><input type="text" value="${user.username}" id="username" name="username"/></td>
        </tr>
        <tr>
            <td class="tableleft">账号</td>
            <td><input type="text" value="${user.account}" id="account" name="account"/></td>
        </tr>
        <tr>
            <td class="tableleft">管理员密码</td>
            <td><input type="text" id="password" name="password"/></td>
        </tr>
        <tr>
            <td class="tableleft">所属学校</td>
            <td>
                <select id="schoolId" name="schoolId">
                    <option value="${user.schoolId}">${user.schoolName}</option>
                    <c:forEach items="${schools}" var="school">
                        <option value="${school.id}">${school.schoolName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td class="tableleft">电话</td>
            <td><input type="text" value="${user.phone}" id="phone" name="phone"/></td>
        </tr>
        <tr>
            <td class="tableleft">email</td>
            <td><input type="text" value="${user.email}" id="email" name="email"/></td>
        </tr>
        <%--<tr>--%>
        <%--<td class="tableleft">性别</td>--%>
        <%--<td><input value="男" id="4" name="gender" type="radio" checked="checked"/>男--%>
        <%--<input   value="女" id="f"  name="gender" type="radio" />女</td>--%>
        <%--</tr>--%>
        <tr>
            <td class="tableleft"></td>
            <td>
                <input type="button" value="提交" id="valiDate" class="btn btn-primary"> &nbsp;&nbsp;
                <button onclick="toAdd()" type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            </td>
        </tr>
    </table>
</form>


</body>
<script type="text/javascript">
    var toAdd = function () {
        window.location.href = "user/list";
    }
    $("#valiDate").click(function () {
        var id = $("#id").val()
        var account = $("#account").val();
        var username = $("#username").val();
        var password = $("#password").val();
        var phone = $("#phone").val();
        var email = $("#email").val();
        var schoolId = $("#schoolId").val();
        if (username == null || username == "") {
            alert("用户名不能为空");
            return;
        }
        if (id == null || id == "") {
            if (password == null) {
                alert("密码不能为空");
                return;
            }
        }
        if (account == null || account == "") {
            alert("账户不能为空");
            return;
        }
        if (schoolId == null || schoolId == "") {
            alert("学校不能为空");
            return;
        }
        $.ajax({
            url: "user/save",
            type: "post",
            async: true,
            contentType: 'application/json; charset=UTF-8',
            dataType: "json",
            data: JSON.stringify({
                "id": id,
                "account": account,
                "password": password,
                "username": username,
                "phone": phone,
                "email": email,
                "schoolId": schoolId
            }),
            success: function (res) {
                if (res.success) {
                    window.location.href = "user/list";
                } else {
                    alert("账号重复");
                }
            },
            error: function (e) {
                alert("网络异常，请稍后再试！");
            }
        });

    });
</script>
</html>