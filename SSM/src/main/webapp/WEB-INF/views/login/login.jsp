<%@include file="commen/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>


<!DOCTYPE html>
<html lang="zh-CN">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>登陆</title>
    <!-- Bootstrap -->
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/style.css" rel="stylesheet">
    <link href="${ctx}/static/login.css" rel="stylesheet">

    <script>
        window.onload=function () {
            //图片切换
            //给表单注册一个事件,用于正则  // var picture = document.getElementsByClassName("picture")[0];
            // picture.onclick=function () {
            //     picture.src="/work3/Picture?time="+new Date();
            // };表达式的校验
            var form = document.getElementsByClassName("form")[0];
            form.onsubmit=function () {
                return reg_user()&&reg_pass();
            };
            function reg_user() {
                var username=document.getElementById("username");
                var str=username.value;
                var reg=/^\w{0,15}[\u4E00-\u9FA5]{0,5}$/;
                if(!reg.test(str)){
                    document.getElementById("username_err").innerHTML="用户名/手机号/邮箱长度在2-15之间!"
                    return false;
                }
                return true;
            }
            function reg_pass() {
                var password=document.getElementById("password");
                var str=password.value;
                var reg=/^[\w]{3,10}$/;
                if(!reg.test(str)){
                    document.getElementById("password_err").innerHTML="密码必须由3-10个数字或字母组成!"
                    return false;
                }
                return true;
            }
        }
    </script>
</head>

<body>
<div class="container mt-200">
    <div class="form row">
        <form class="form-horizontal col-sm-offset-3 col-md-offset-3" action="/ssm/login/verify" method="post" id="login_form">
            <h3 class="form-title">用户管理系统</h3>
            <br>
            <font class="form_message" ><c:if test="${!empty error_mes}">${error_mes}</c:if></font>
            <div class="col-sm-6 col-md-6">
                <table>
                    <tr>
                        <td class="text">
                            用户名:
                        </td>
                        <td>
                            <div class="form-group">
                                <!--<i class="fa fa-user fa-lg"></i>-->
                                <input id="username" class="form-control required text_input" type="text" placeholder="用户名/手机号/邮箱" name="username" autofocus="autofocus" maxlength="20"/>
                            </div>
                        </td>
                        <td>
                            <span id="username_err"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="text">
                            密码:
                        </td>
                        <td>
                            <div class="form-group">
                                <!--<i class="fa fa-lock fa-lg"></i>-->
                                <input id="password" class="form-control required text_input" type="password" placeholder="密码" name="password" maxlength="20"/>
                            </div>
                        </td>
                        <td>
                            <span id="password_err"></span>
                        </td>
                    </tr>
                    <%--<tr>--%>
                        <%--<td class="text">--%>
                            <%--验证码:--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<input type="text" name="verify_code" class="verify_input">--%>
                            <%--<img src="/work3/Picture" class="picture">--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                </table>
                <div class="form-group">
                    <input type="submit" class="btn btn-success pull-right submit_self" value="Login "/>
                </div>
            </div>
        </form>
    </div>
</div>
<h4 class="form_message"></h4><!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="${ctx}/static/js/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="${ctx}/static/js/bootstrap.min.js"></script>
</body>
</html>