<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page deferredSyntaxAllowedAsLiteral="true"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath}/"/>
<title></title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="Css/style.css" />
<script type="text/javascript" src="Js/jquery.js"></script>
<script type="text/javascript" src="Js/bootstrap.js"></script>
<script type="text/javascript" src="Js/ckform.js"></script>
<script type="text/javascript" src="Js/common.js"></script>
<link href="assets/layui/css/layui.css" rel="stylesheet" />
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
		<form class="layui-form" action="">
			<div id="layui-xtree-demo1" style="width:300px;height:500px; border:1px solid black; margin:20px;"></div>

			<input type="button" value="提交" id="btn_getCk" />

		</form>
	</body>

</html>
<!--引入你自己的layui文件-->
<script src="assets/layui/layui.js"></script>
<!--xtree的js文件-->
<script src="assets/layui-xtree/layui-xtree.js"></script>
<script type="text/javascript">
    // 已经选中的菜单
    var checkeds = '${checkeds }';
    checkeds = eval(checkeds);
    // 所有的菜单
    var menus = '${menus }';
    menus = eval(menus);

    var level = '${level }';

    var json = [];
    // 封装tree数据
    for (var i = 0; i < menus.length; i++) {
        var item = {
            title: menus[i].menuname,
            value: menus[i].id,
            data: []
        };
        json.push(item);
    }

    // 将数据渲染到页面
    layui.use("form", function () {
        var form = layui.form;

        var xtree = new layuiXtree({
            elem: 'layui-xtree-demo1',
            form: form,
            data: json,
            color: "#000",
            icon: {
                open: "&#xe7a0;",
                close: "&#xe622;",
                end: "&#xe621;"
            }
        });

        // 默认选中
        for (var i = 0; i < checkeds.length; i++) {
            var id = checkeds[i].id;
            id = "input-" + id;
            var ele = document.getElementById(id);
            var xtree_p = ele.parentNode.parentNode;
            var xtree_all = xtree_p.getElementsByClassName("layui-xtree-item");
            xtree_p.getElementsByClassName("layui-xtree-checkbox")[id].checked = true;
            xtree_p.getElementsByClassName("layui-xtree-checkbox")[id].nextSibling.classList.add('layui-form-checked');
        }

        // 点击提交
        $("#btn_getCk").click(function () {

            var menuIds = "";

            // 获取已经选中的checkbox
            var oCks = xtree.GetChecked();

            // 获取已选中的checkbox的value
            for (var i = 0; i < oCks.length; i++) {
                var value = oCks[i].value;
                menuIds += value + ",";
            }

            var param = "level=" + level + "&menuIds=" + menuIds;

            $.ajax({
                url: "role/edit",
                type: "post",
                async: true,
                data: param,
                success: function (res) {
                    if (res == "success") {
                        window.location.href = "role/list";
                    } else {
                        alert(res);
                    }
                },
                error: function (e) {
                    alert("网络异常");
                }
            });

        });


    });
	
	
	
	
	
	
</script>
