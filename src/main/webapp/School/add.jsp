<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page deferredSyntaxAllowedAsLiteral="true"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/" />
    <title></title>
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
<form action="school/add" method="post">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td class="tableleft">学校名称</td>
        <td ><input type="text" id="schName" name="schName"/></td>
    </tr>
    <tr>
        <td class="tableleft">需要援助</td>
        <td >
        	<input type="radio" value="1" checked/> 是
        	&nbsp;&nbsp;&nbsp;&nbsp;
           	<input type="radio" value="0" /> 否	
        </td>
    </tr>
    <tr>
        <td class="tableleft">前往援助</td>
        <td >
        	<input type="radio" value="1" checked/> 是
        	&nbsp;&nbsp;&nbsp;&nbsp;
           	<input type="radio" value="0" /> 否
        </td>
    </tr>
    <tr>
    	<td class="tableleft">详细信息</td>
    	<td>
    		<div>
		    	<!-- <textarea rows="5" cols="5"></textarea> -->
		    	<script type="text/plain" id="info" name="info" style="width: 600px;height: 400px;"></script>
	    	</div>	
    	</td>
    </tr>
    <tr>
    	<td class="tableleft">援藏历史</td>
    	<td>
    	<div>
		    <script type="text/plain" id="history" name="history" style="width: 600px;height: 400px;"></script>
	    </div>
	    </td>
    </tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" id="submit-button" class="btn btn-primary">保存</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form>
</body>
<script type="text/javascript">
    var info = UE.getEditor("info",{
    	autoFloatEnabled: false
    });
    var hist = UE.getEditor("history",{
    	autoFloatEnabled: false
    });
    
  
</script>
</html>