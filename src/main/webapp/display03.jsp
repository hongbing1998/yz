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
<form action="work/edit" method="post" id="app">
    <table class="table table-bordered table-hover definewidth m10" v-for="(value,index) in employment">
        <tr>
            <td width="10%" class="tableleft">编号</td>
            <td>{{value.id}}</td>
        </tr>
        <tr>
            <td class="tableleft">标题</td>
            <td >{{value.title}}</td>
        </tr>
        <tr>
            <td class="tableleft">学校</td>
            <td>{{value.content}}</td>
        </tr>
        <tr>
            <td class="tableleft">详细信息</td>
            <td>
                {{value.createDate}}
            </td>
        </tr>
        <tr>
            <td class="tableleft">援藏历史</td>
            <td>
                {{value.userId}}
            </td>
        </tr>
    </table>
</form>
</body>
<script src="index/js/jquery-3.3.1.min.js"></script>
<script src="index/js/vue.js"></script>
<script type="text/javascript">
    new Vue({
        el:'#app',
        data:{
            employment:[],
        },
        methods:{
        },
        created: function employ(){
            $.ajax({
                url:'/employment/list',
                type:'get',
                dataType:'json',
                data:'',
                success:(result)=>{
                if(result.success){
                console.log(result.resultObj)
                this.employment=result.resultObj;
            }
        },
        });
        }
    })
</script>
</html>