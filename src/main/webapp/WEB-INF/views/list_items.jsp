<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 20.10.2018
  Time: 5:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Items List</h3>
<c:forEach var="items" items="${listItems}">
    <tr>
        <td>${items.itemId}</td>
        <td>${items.itemName}</td>
        <td>${items.price}</td>
        <td>${items.weight}</td>
        <td>${items.volume}</td>
        <td>${items.availableCount}</td>
        <td>${items.pic}</td>
    </tr>
</c:forEach>
</body>
</html>
