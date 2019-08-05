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
    <title>学生管理系统_系统登录</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="container mt-100">
    <div class="form row">
        <div class="col-lg-12 col-md-12 text-center">
            <ul class="list-group">
			  <li class="list-group-item"><a class="btn btn-link" href="${ctx}/Student">学生信息管理</a></li>
			  <li class="list-group-item"><a class="btn btn-link" href="javascript:;">教师信息管理</a></li>
			  <li class="list-group-item"><a class="btn btn-link" href="javascript:;">成绩管理</a></li>
			  <li class="list-group-item"><a class="btn btn-link" href="javascript:;">课程管理</a></li>
			</ul>
			
        </div>
    </div>
</div>

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="js/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="js/bootstrap.min.js"></script>
<script src="js/sweetalert.min.js"></script>
</body>
</html>