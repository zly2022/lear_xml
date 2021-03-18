<%--
  Created by IntelliJ IDEA.
  User: 阿阳
  Date: 2021/3/15
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        //request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
    %>
    欢迎 <%=name%> 登录本系统，您的初始密码为 <%=password%>
</body>
</html>
