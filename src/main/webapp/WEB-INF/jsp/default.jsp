<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 路径 getContextPath时有'/'的，即：/myWeb
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
<head>
    <title>即时聊天系统</title>
    <base href="<%=basePath%>"/>
</head>
<body>
<div align="center">
    <br>
    提示信息：${msg} <br>
    系统异常信息：${ex} <br>
</div>
</body>
</html>
