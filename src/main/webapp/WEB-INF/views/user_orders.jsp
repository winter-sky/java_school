<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alisa
  Date: 11/8/2018
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Current orders</title>
</head>
<body>
<h3>Your current orders</h3>
<c:forEach var="order" items="${orderlist}">
    <tr>
    <p>Order Id: ${order.orderId}<br></p>
    <p>Full price: ${order.orderPrice}<br></p>
    <p>Order Status: ${order.orderStatus.toString()}<br></p>
    <c:forEach var="orderitem" items="${order.orderItems}">
        <tr>
            <p><img src="${orderitem.item.pic}" alt="some pic"></p>
            <p>Book name: ${orderitem.item.itemName}</p>
        </tr>
    </c:forEach>
    </tr>
</c:forEach>
</body>
</html>
