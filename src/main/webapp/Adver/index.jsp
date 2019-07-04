<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page deferredSyntaxAllowedAsLiteral="true"%>

<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath}/" />
<title></title>
<meta charset="UTF-8">
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

@media ( max-width : 980px) {
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
<form class="form-inline definewidth m20" action="/employment/list"
      method="get">
    标题： <input type="text" name="title"
               class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
    <input type="submit" class="btn btn-primary" value="查询">
		&nbsp;&nbsp;
    <a class="btn btn-success" href="/Adver/add.jsp">新增需求</a>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>编号</th>
				<th>标题</th>
				<th>最后更新时间</th>
				<th>发布者</th>
				<th>操作</th>
			</tr>
		</thead>

        <c:forEach items="${advers}" var="data">
            <tr>
                <td>${data.id}</td>
                <td style="max-width: 220px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
                    <a class="btn btn-primary" title="点击查看详情" href="/employment/Adver/${data.id}">${data.title}</a>
                </td>
                <td>${data.createDate}</td>
                <td>${data.userName}</td>
                <td>
                    <button class="btn btn-success" id="delete-buttonb" onclick="del('${data.id}')">删除</button>
                </td>
            </tr>
        </c:forEach>

	</table>
</body>
<script>
    $(function () {

    });

    function del(id) {
        if (confirm("确定要删除吗？")) {
            $.ajax({
                url: "/employment/delete/" + id,
                async: false,
                type: "get",
                success: function () {
                    alert("删除成功");
                    window.location.href = "/employment/list";
                },
                error: function () {
                    alert("netword is error");
                }
            });
        }
    }
</script>
</html>