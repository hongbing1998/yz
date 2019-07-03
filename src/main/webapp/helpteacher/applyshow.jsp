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
<form action="" method="post">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">姓名</td>
            <td>${Apply.name}</td>
        </tr>
        <tr>
            <td class="tableleft">QQ</td>
            <td>${Apply.QQ}</td>
            <td class="tableleft">电话</td>
            <td>${Apply.telephone}</td>
        </tr>
        <tr>
            <td class="tableleft">学校</td>
            <td>${Apply.school}</td>
            <td class="tableleft">课程</td>
            <td>${Apply.crouse}</td>
        </tr>
        <tr>
            <td class="tableleft">招聘编号</td>
            <td>${Apply.employid}</td>
        </tr>
        <tr>
            <td class="tableleft">招聘标题</td>
            <td><<a href="/employment/Adver/apply/${data.id}">${Apply.employtitle}</a></td>
        </tr>
        <tr>
            <td class="tableleft">时间</td>
            <td>${Apply.createDate}</td>
        </tr>
        <tr>

            <td colspan="2"><textarea name="content" style="width:1000px;height:300px;">${Adver.content}</textarea>
            </td>

        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <a href="/employment/list" class="btn btn-success" name="backid" id="backid">返回列表</a>
                <%--<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>--%>
            </td>
        </tr>
    </table>
</form>
</body>
<script type="text/javascript">

</script>
</html>