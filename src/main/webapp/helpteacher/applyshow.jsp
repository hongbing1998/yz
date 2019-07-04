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
<form action="/employment/save" method="post">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">信息编号</td>
            <td>${Adver.id}</td>
        </tr>
        <tr>
            <td class="tableleft">标题</td>
            <td>${Adver.title}</td>
        </tr>
        <tr>
            <td class="tableleft">撰写人</td>
            <td>${Adver.userName}</td>
        </tr>
        <tr>
            <td class="tableleft">时间</td>
            <td>${Avter.createDate}</td>
        </tr>
        <tr>

            <td colspan="2"><textarea name="content" style="width:1000px;height:300px;"
                                      readonly="ture">${Adver.content}</textarea>
            </td>

        </tr>
        <tr>
            <td>
                <a href="/apply/apply/${applyId}" class="btn btn-success" name="backid" id="backid">返回</a>
            </td>
        </tr>
    </table>
</form>
</body>
<script type="text/javascript">

</script>
</html>