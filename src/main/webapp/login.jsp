<%--
  Created by IntelliJ IDEA.
  User: 阿阳
  Date: 2021/3/17
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
手机库房系统</br>
<form action="login" method="post">
    账号：<input type="text" name="name"><br>
    密码：<input type="password" name="password"><br>
    <input type="submit" name="登录"><input type="reset" name="重置">
    <%if(request.getParameter("flg")!=null){%>
    <span style="color: red">账号或密码错误</span>
    <%}%>
</form>
</body>
</html>
