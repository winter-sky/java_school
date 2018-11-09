<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Verman
  Date: 09.11.2018
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Top items</title>
</head>
<body>
<h1>Best selling items</h1>
<table>
    <tr>
        <td>Item</td>
        <td>Category</td>
        <td>Price</td>
        <td>Weight</td>
        <td>Volume</td>
        <td>Available count</td>
    </tr>
    <c:forEach var="item" items="${listItems}">
    <tr>
        <td>${item.itemName}</td>
        <td>${item.category.getCategoryName()}</td>
        <td>${item.price}</td>
        <td>${item.weight}</td>
        <td>${item.volume}</td>
        <td>${item.availableCount}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
