<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alisa
  Date: 11/7/2018
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Current order</title>
</head>
<body>
<c:forEach var="item" items="${listorderitems}">
    <tr>
        <p><img src="${item.pic}" alt="some pic"></p>
        <p> Book Name: ${item.itemName}</p>
    </tr>
</c:forEach>
</body>
</html>
