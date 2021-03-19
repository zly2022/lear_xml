<%@ page import="com.bean.Phone" %><%--
  Created by IntelliJ IDEA.
  User: 阿阳
  Date: 2021/3/19
  Time: 0:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%Phone phone = (Phone) request.getAttribute("phone");%>
    <form action="update" method="post">
        <input type="hidden" name="id" value="<%=phone.getId()%>" >
        <input type="text" name="name" value="<%=phone.getName()%>">
        <input type="text" name="price" value="<%=phone.getPrice()%>">
        <input type="text" name="ms" value="<%=phone.getMs()%>">
        <input type="submit" name="修改">
    </form>
</body>
</html>
