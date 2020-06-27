<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 2020/6/19
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<form action="/spww/user/register" method="post">
    账号: <input type="text" placeholder="请输入账号" name="name">
    </br>
    密码：<input type="password" placeholder="输入密码" name="password">
    </br>
    <button type="submit">注册</button></form>
</form>
</body>
</html>
