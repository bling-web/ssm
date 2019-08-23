<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/14 0014
  Time: 下午 8:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <form action="/ssm/User/insert" method="post">
        id:<input type="text" name="id">  <br/>
        usename:<input type="text" name="username"><br/>
        name:<input type="text" name="name"><br/>
        phone:<input type="text" name="phone"><br/>
        email:<input type="text" name="email"><br/>
        password:<input type="text" name="password"><br/>
        date:<input type="text" name="sex"><br/>
        sign:<input type="text" name="date"><br/>
        address:<input type="text" name="address"><br/>
        <input type="submit" value="提交"><br/>
    </form>
</table>

</body>
</html>
