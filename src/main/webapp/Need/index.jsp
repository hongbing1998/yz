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

        @media ( max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }

        .option-button:hover {
            color: red;
        }
    </style>
</head>
<body>
<form class="form-inline definewidth m20" action="demand/condition_quey" method="post">
    标题： <input type="text" name="title" id="param" class="abc input-default" placeholder="输入查询标题" value="">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>
    <button type="button" class="btn btn-success" onclick="window.location.href='demand/add'" id="addnew">发布需求</button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>编号</th>
        <th>标题</th>
        <th>紧急状态</th>
        <th>发布学校</th>
        <th>发布时间</th>
        <th>发布者</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach items="${page.records}" var="demand" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <td style="max-width: 220px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
                <a title="点击查看全文" href="#">${demand.title}</a>
            </td>
            <td>
                <c:choose>
                    <c:when test="${demand.level eq 1}">紧急</c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${demand.level eq 2}">非常紧急</c:when>
                </c:choose>
            </td>
            <td style="max-width: 120px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">${demand.schoolName}</td>
            <td>${demand.createDate}</td>
            <td>${demand.userName}</td>
            <td>
                <a class="option-button" href="#">编辑</a>
                <a class="option-button" href="/demand/delete/${demand.id}">删除</a>
                <a class="option-button" href="#">查看</a>
            </td>
        </tr>
    </c:forEach>
</table>
<div class="inline pull-right page">
    ${page.total}条记录 ${page.current}/${page.pages}页
    <a href='#'>首页</a>
    <a href='#'>上一页</a>
    <a href='#'>下一页</a>
    <a href='#'>尾页</a>
</div>
</body>
<%--<script type="text/javascript">--%>
<%--    function conditionQuery() {--%>
<%--        var title = $("#param").val();--%>
<%--        $.ajax({--%>
<%--            url: "demand/condition_quey",--%>
<%--            type: "POST",--%>
<%--            async: false,--%>
<%--            dataType: "json",--%>
<%--            data: {--%>
<%--                "title": title--%>
<%--            },--%>
<%--            success: function (res) {--%>
<%--                $.ajax({--%>
<%--                    url: "policy/toPindex",--%>
<%--                    type: "get",--%>
<%--                    async: false,--%>
<%--                    success: function (res) {--%>
<%--                        window.location.reload();--%>
<%--                    }--%>
<%--                });--%>
<%--            }--%>
<%--        });--%>
<%--    }--%>
<%--</script>--%>
</html>