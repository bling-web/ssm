<%@include file="../login/commen/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>


<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <title>注册页面</title>
    <link href="${ctx}/static/register.css" rel="stylesheet">
    <script>
        window.onload=function () {
            document.getElementById("username_id").value="${user.username}";
            document.getElementById("password_id").value="${user.password}";
            document.getElementById("email_id").value="${user.email}";
            document.getElementById("phone_id").value="${user.phone}";
            document.getElementById("name_id").value="${user.name}";
            document.getElementById("date_id").value="${user.date}";
            //图片切换
            var picture = document.getElementById("idenfy_img");
            picture.onclick=function () {
                picture.src="/ssm/picture/get?time="+new Date();
            };
            var form = document.getElementsByClassName("form")[0];
            form.onsubmit=function () {
                return reg_user()&&reg_pass()&&reg_email&&reg_phone();
            };
            function reg_user() {
                var username=document.getElementById("username_id");
                var str=username.value;
                var reg=/^[\u4E00-\u9FA5]{2,5}$/;
                if(!reg.test(str)){
                    document.getElementById("username_err").innerHTML="由2-5个汉字组成!";
                    return false;
                }
                return true;
            }
            function reg_pass() {
                var password=document.getElementById("password_id");
                var str=password.value;
                var reg=/^\w{3,10}$/;
                if(!reg.test(str)){
                    document.getElementById("password_err").innerHTML="由3-10个数字,字母组成";
                    return false;
                }
                return true;
            }
            function reg_email() {
                var password=document.getElementById("email_id");
                var str=password.value;
                var reg=/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
                if(!reg.test(str)){
                    document.getElementById("email_err").innerHTML="请输入正确的邮箱号";
                    return false;
                }
                return true;
            }
            function reg_phone() {
                var password=document.getElementById("phone_id");
                var str=password.value;
                var reg=/^1[34578]\d{9}$/;
                if(!reg.test(str)){
                    document.getElementById("phone_err").innerHTML="请输入正确的手机号!"
                    return false;
                }
                return true;
            }
        }
    </script>



</head>
<body>
    <div class="register">
        <form action="/ssm/User/addUser" class="form" method="post" enctype="multipart/form-data">
        <div class="left">
            <div >
                <a href="#" >上传头像</a>
            </div>
            <div >
                <c:if test="${!empty name}">
                    <img  id="picture" src="/ssm/static/uploads/${name}"  />
                </c:if>
            </div>
                <input type="file" name="Myfile" value="上传头像">

        </div>
        <div class="center">
                <table>
                    <tr>
                        <td>
                           用户名:
                        </td>
                        <td>
                            <input class="center_common" type="text" id="username_id"   name="username" placeholder="请输入用户名">
                            <span id="username_err" ></span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                         密码:
                        </td>
                        <td>
                            <input class="center_common" type="password" id="password_id" name="password" placeholder="请输入密码">
                            <span id="password_err"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            邮箱:
                        </td>
                        <td>
                            <input class="center_common" type="email" id="email_id" name="email" placeholder="请输入邮箱">
                            <span id="email_err"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                             真实姓名:
                        </td>
                        <td>
                            <input class="center_common" type="text"id="name_id" name="name" placeholder="请输入你的真实姓名">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            手机号:
                        </td>
                        <td>
                            <input class="center_common" type="quantity"id="phone_id" name="phone" placeholder="请输入你的手机号" >
                            <span id="phone_err"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            性别:
                        </td>
                        <td>
                            <input type="radio" checked name="sex" value="1" >:男
                            <input type="radio" name="sex" value="2">:女
                        </td>
                    </tr>
                    <tr>
                        <td>
                            出生日期:
                        </td>
                        <td>
                            <input class="center_common" type="date" id="date_id"  name="date" placeholder="请输入你的真实姓名">
                        </td>
                    </tr>

                    <tr>
                        <td>
                             验证码:
                        </td>
                        <td>
                            <input  class="center_reg" type="text" name="identifying_code">
                            <img  id="idenfy_img" src="/ssm/picture/get">
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input class="sub" type="submit"/>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td >
                            <font color="red" >
                                <c:if test="${!empty error_user}">
                                    ${error_user}
                                </c:if>
                            </font>
                        </td>
                    </tr>
                </table >

        </div>
        </form>
        <div class="right">
            <p>已有账号?<a href="/ssm/login/to" >立即登录</a></p>
        </div>
    </div>


</body>
</html>