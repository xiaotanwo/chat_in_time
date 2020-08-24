<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
    <head>
        <title>即时聊天系统</title>
        <base href="<%=basePath%>"/>
    </head>
    <body style="background: url(static/bg/login.jpg) no-repeat center center">
        <div align="center">
            <br>
            <h1>即时聊天系统，登录页面</h1>
            <br>
            <form action="login" method="post">
                用户：<input name="name" type="text"/><br><br>
                密码：<input name="password" type="password"/><br><br>
                <input type="submit" value="登录">
                <a href="to_register">
                    <input  type="button" value="注册">
                </a>
            </form>
        </div>
    </body>
</html>
