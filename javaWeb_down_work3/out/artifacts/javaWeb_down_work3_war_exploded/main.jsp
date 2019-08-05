<%@include file="commen/taglib.jsp"%>
<%--有一个专门用来导包和给常变量起别名的文件.这里直接引入该文件即可--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>库存管理系统</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <style>
        table tr:first-child td:last-child{
            text-align: center;
            width: 7cm;
        }
        td{
            text-align: center;
        }
        td a{
            margin-left: 25px;
            margin-right: 25px;
        }
    </style>
</head>
<body>

<div class="container mt-100">
    <div class="form row">
        <div class="col-lg-12 col-md-12 text-center">
            <h1>商品管理</h1>
        </div>
        <div class="col-lg-12 col-md-12 mb-10">
            <a class="btn btn-info pull-right" href="add.jsp">添加商品</a>
        </div>
        <table class="table table-bordered">
            <tr class="info">
                <td>编号</td>
                <td>商品名称</td>
                <td>重量</td>
                <td>大小</td>
                <td>数量</td>
                <td>单价</td>
                <td>添加日期</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${goods}" var="i" varStatus="s">
                <tr>
                    <td>${i.number}</td>
                    <td>${i.name}</td>
                    <td>${i.weight}</td>
                    <td>${i.size}</td>
                    <td>${i.quantity}</td>
                    <td>${i.price}</td>
                    <td>
                        <fmt:formatDate value="${i.addDate}"/>
                    </td>
                    <td class="warning text-center">
                        <a class="btn btn-warning" href="${ctx}/GoodsUptHelp?id=${i.id}">修改</a>
                        <a class="btn btn-danger" href="javascript:delUser(${i.id})">
                        删除
                    </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="js/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="js/bootstrap.min.js"></script>
<script src="js/sweetalert.min.js"></script>
<script>
    function delUser(id) {
        swal("确认删除学生信息吗?", {
            buttons: {
                cancel: true,
                confirm: true
            }
        }).then(function (value) {
            if(value){
                window.location="${ctx}/GoodsDel?id="+id;
            }
        });

    }
</script>
</body>
</html>