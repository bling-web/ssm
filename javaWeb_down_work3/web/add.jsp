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
    <title>库存管理系统-添加商品</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>

<div class="container mt-100">
    <div class="form row">
        <div class="form row">
            <form class="form-horizontal col-sm-offset-3 col-md-offset-3" action="${ctx}/GoodsAdd" method="post" id="login_form">
                <div class="col-sm-6 col-md-6">
                    <div class="form-group">
                        <h1>添加商品</h1>
                        <h3 >${error_mes}</h3>
                    </div>
                    <div class="form-group">
                    <label for="number">编号</label>
                    <input class="form-control required" type="text" id="number" placeholder="请输入商品编号" name="number" autofocus="autofocus" maxlength="20"/>
                    </div>
                    <div class="form-group">
                        <label for="name">姓名：</label>
                        <input class="form-control required" type="text" id="name"  placeholder="请输入商品姓名" name="name" autofocus="autofocus" maxlength="20"/>
                    </div>
                    <div class="form-group">
                        <label for="weight">重量：</label>
                        <input class="form-control required" type="text" id="weight"  placeholder="请输入商品重量" name="weight" autofocus="autofocus" maxlength="20"/>
                    </div>
                    <div class="form-group">
                        <label for="size">大小：</label>
                        <input class="form-control required" type="text" id="size"  placeholder="请输入商品体积" name="size" autofocus="autofocus" maxlength="20"/>
                    </div>
                    <div class="form-group">
                        <label for="quantity">数量：</label>
                        <input class="form-control required" type="text" id="quantity"  placeholder="请输入商品数量" name="quantity" autofocus="autofocus" maxlength="20"/>
                    </div>
                    <div class="form-group">
                        <label for="price">单价：</label>
                        <input class="form-control required" type="text" id="price"  placeholder="请输入商品单价" name="price" autofocus="autofocus" maxlength="20"/>
                    </div>
                    <div class="form-group">
                        <label for="addDate">添加日期：</label>
                        <input type="date" class="form-control" id="addDate" name="addDate" placeholder="请输入出生日期">
                    </div>
                    <br/>
                    <div class="form-group" style="text-align: center">
                        <input class="btn btn-info" type="submit" value="提交" />
                        <input class="btn btn-warning" type="reset" value="重置" />
                        <a class="btn btn-default" href="${ctx}/Goods_servlet" >返回</a>
                    </div>
                    <br/>
                    <br/>
                    <br/>
                    <br/>

                </div>
            </form>

        </div>
    </div>

</div>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="js/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>