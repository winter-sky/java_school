<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Current order</title>
</head>
<body>
<c:if test="${!empty listorderitems}">
<p> Full order price: ${currentorder.orderPrice}</p>
<c:forEach var="item" items="${listorderitems}">
    <tr>
        <p><img src="${item.pic}" alt="some pic" width="184" height="250"></p>
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
</c:if>
</body>
</html>
