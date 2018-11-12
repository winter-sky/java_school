<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Top items</title>
</head>
<body>
<a href="/adminpage">Back to admin page</a><br>
<h1>Best selling items</h1>
<table>
    <tr>
        <td>Item</td>
        <td>Category</td>
        <td>Price</td>
        <td>Weight</td>
        <td>Volume</td>
        <td>Available count</td>
        <td>Quantity sold</td>
    </tr>
    <c:forEach var="item" items="${listItems}">
    <tr>
        <td>${item.getItem().itemName}</td>
        <td>${item.getItem().category.getCategoryName()}</td>
        <td>${item.getItem().price}</td>
        <td>${item.getItem().weight}</td>
        <td>${item.getItem().volume}</td>
        <td>${item.getItem().availableCount}</td>
        <td>${item.getQuantitiesSold()}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
