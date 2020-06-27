<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<body>
<h2>登录</h2>
<form action="/spww/user/login" method="post">
    账号: <input type="text" placeholder="请输入账号" name="name">
    </br>
    密码：<input type="text" placeholder="输入密码" name="password">
    </br>
    <button type="submit">登录</button></form>
    <br/>
    <a href="/spww/user/register">用户注册</a>
</form>
</body>
</html>
