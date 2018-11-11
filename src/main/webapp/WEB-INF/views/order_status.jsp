<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alisa
  Date: 11/9/2018
  Time: 7:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Select Order Status</title>
</head>
<body>
<h3>Select Order Status</h3>
<c:forEach var="status" items="${orderstatus}">
    <tr>
            ${status.toString()}<br>
        <a href="<c:url value='/selectorderstatus/${status}/${order.orderId}'/>">select</a><br>
    </tr>
</c:forEach>
</body>
</html>