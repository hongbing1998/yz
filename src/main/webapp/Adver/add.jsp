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
<form action="/employment/save" method="post" onsubmit="return check()">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td class="tableleft">标题</td>
        <td ><input type="text" id="title" name="title" value="" style="width: 360px;"/></td>
    </tr>
    <tr>
    	<td class="tableleft">详细信息</td>
    	<td>
	    	<div>
                <textarea name="content" rows="5" cols="5"></textarea>
	    	</div>
    	</td>
    </tr>
   
    <tr>
        <td class="tableleft"></td>
        <td>
            <input type="submit" class="btn btn-primary" value="保存">
            <a href="/employment/list" class="btn btn-success" name="backid" id="backid">返回列表</a>
        </td>
    </tr>
</table>
</form>
</body>
<script type="text/javascript">

    function check() {
        var title = $("#title").val();
        var content = $("#content").val();

        if (title == null || title == "") {
            alert("标题不能为空");
            return false;

        }

        if (content == null || content == "") {
            alert("详情不能为空不能为空");
            return false;

        }

        return true;

    }
</script>
</html>