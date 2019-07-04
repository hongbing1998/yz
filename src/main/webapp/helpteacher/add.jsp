<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page deferredSyntaxAllowedAsLiteral="true" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/"/>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="./Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="./Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="./Css/style.css"/>
    <script type="text/javascript" src="./Js/jquery.js"></script>
    <script type="text/javascript" src="./Js/bootstrap.js"></script>
    <script type="text/javascript" src="./Js/ckform.js"></script>
    <script type="text/javascript" src="./Js/common.js"></script>
    <script type="text/javascript" charset="utf-8" src="./ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="./ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="./ueditor/lang/zh-cn/zh-cn.js"></script>
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
    </style>
</head>
<body>
<form action="/apply/save" method="post" onsubmit="return check()">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td class="tableleft">姓名</td>
            <td><input type="text" id="name" name="name" style="width: 360px;"/></td>
        </tr>
        <tr>
            <td class="tableleft">QQ</td>
            <td><input type="text" id="qq" name="qq" style="width: 360px;"/></td>
        </tr>
        <tr>
            <td class="tableleft">电话</td>
            <td><input type="text" id="telephone" name="telephone" style="width: 360px;"/></td>
        </tr>
        <tr>
            <td class="tableleft">学校</td>
            <td><select name="school" id="school">
                <c:forEach items="${schools}" var="school">
                    <option value="${school.schoolName}">${school.schoolName}</option>
                </c:forEach>
            </select>
            </td>
        </tr>
        <tr>
            <td class="tableleft">招聘信息标题</td>
            <td><select name="employId" id="employId">
                <c:forEach items="${employs}" var="employ">
                    <option value="${employ.id}">${employ.title}</option>
                </c:forEach>
            </select></td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <input type="submit" class="btn btn-primary" value="报名">
            </td>
        </tr>
    </table>
</form>
</body>
<script type="text/javascript">

    function check() {
        var name = $("#name").val();
        var qq = $("#qq").val();
        var telephone = $("#telephone").val();

        if (name == null || name == "") {
            alert("标题不能为空");
            return false;

        }

        if (qq == null || qq == "") {
            alert("详情不能为空不能为空");
            return false;

        }
        if (telephone == null || telephone == "") {
            alert("标题不能为空");
            return false;

        }

        return true;

    }

</script>
</html>