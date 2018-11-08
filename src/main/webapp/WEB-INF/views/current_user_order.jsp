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
<p> Full order price: ${currentorder.orderPrice}</p>
<c:forEach var="item" items="${listorderitems}">
    <tr>
        <p><img src="${item.pic}" alt="some pic"></p>
        <p> Book Name: ${item.itemName}</p>
        <p> Author: ${item.params.author}</p>
        <p> Language: ${item.params.language}</p>
        <p> Format: ${item.params.format}</p>
    <c:forEach var="orderitem" items="${orderitem}">
        <c:if test="${orderitem.order.orderId eq currentorder.orderId && orderitem.item.itemId eq item.itemId}">
            <p>Quantity: ${orderitem.itemQuantity}</p>
        </c:if>
    </c:forEach>
        <br><br>
    </tr>
</c:forEach>
<p><a href="/paymentmethod">Complete order</a></p>

</body>
</html>
