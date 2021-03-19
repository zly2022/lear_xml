<%@ page import="com.bean.Phone" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 阿阳
  Date: 2021/3/18
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%List<Phone> phone = (List<Phone>) request.getAttribute("phone");%>
<%if (phone.size() == 0) {%>
<span style="color: red">暂时没有货物</span>
<%} else {%>
<table border="1px" cellpadding="0" cellspacing="0">
    <%
        for (Phone p : phone
        ) {
    %>
    <tr>
        <td><%=p.getId()%>
        </td>
        <td><%=p.getName()%>
        </td>
        <td><%=p.getPrice()%>
        </td>
        <td><%=p.getMs()%>
        </td>
        <td><a href="update?id=<%=p.getId()%>&flg=1">修改</a></td>
    </tr>
    <%}%>
</table>
<%}%>
</body>
</html>
