<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page deferredSyntaxAllowedAsLiteral="true"%>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/"/>
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
<form action="/work/save" method="post" onsubmit="return check()">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td class="tableleft">标题</td>
            <td><input type="text" id="title" name="title" style="width: 360px;"/></td>
        </tr>
        <tr>
            <td class="tableleft">政策详情</td>
            <td>
                <%--                <textarea type="text" name ="content" rows="5" cols="5"></textarea>--%>
                <input type="text" id="content" name="content"/>
            </td>
        </tr>

        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="submit" id="submit-button" class="btn btn-primary">保存</button> &nbsp;<a
                    href="/work/toindex">&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">
                返回列表
            </button>
            </a>
            </td>
        </tr>
    </table>
</form>
</body>
<script type="text/javascript">

    function check(){
        var title = $("#title").val();
        var content = $("#content").val();

        if(title == null || title == ""){
            alert("标题不能为空");
            return false;

        }

        if(content == null || content == ""){
            alert("详情不能为空不能为空");
            return false;

        }

        return true;
    }
</script>
</html>