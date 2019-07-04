<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page deferredSyntaxAllowedAsLiteral="true" %>

<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/"/>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="./Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css"
          href="./Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="./Css/style.css"/>
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

        @media ( max-width: 980px) {
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
        <input id="id" name="id" style="display: none" value="${role.id}">
        <tr>
            <td class="tableleft">角色名</td>
            <td><input type="text" id="rolename" name="rolename"/></td>
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
        window.location.href = "role/list";
    }
    $("#valiDate").click(function () {
        var rolename = $("#rolename").val();
        var id = $("#id").val();
        if (rolename == null || path == "") {
            alert("角色名不能为空");
            return;
        }
        $.ajax({
            url: "role/save",
            type: "post",
            async: true,
            contentType: 'application/json; charset=UTF-8',
            dataType: "json",
            data: JSON.stringify({
                "roleName": rolename,
                "id": id
            }),
            success: function (res) {
                if (res.success) {
                    window.location.href = "role/list";
                } else {
                    alert("网络异常，请稍后再试！");
                }
            },
            error: function (e) {
                alert("网络异常，请稍后再试！");
            }
        });

    });
</script>
</html>