<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alisa
  Date: 11/9/2018
  Time: 7:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>All orders</h3>
<c:forEach var="order" items="${listallorders}">
    <tr>
    <p>Order Id: ${order.orderId}<br></p>
    <p>Client: ${order.client.firstName} ${order.client.lastName}<br></p>
    <p>Order Status: ${order.orderStatus.toString()}<br></p>
    <p><a href="<c:url value='/changeorderstatus/${order.orderId}'/>">Edit status</a></p>
    <p>Payment Status: ${order.paymentStatus.toString()}<br></p>
    <c:forEach var="orderitem" items="${order.orderItems}">
        <tr>
            <p><img src="${orderitem.item.pic}" alt="some pic"></p>
            <p>Book name: ${orderitem.item.itemName}</p>
        </tr>
    </c:forEach>
    <p>Full price: ${order.orderPrice}<br></p>
    </tr>

</c:forEach>
</body>
</html>
