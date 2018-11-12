<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <p><img src="${orderitem.item.pic}" alt="some pic" width="184" height="250"></p>
            <p>Book name: ${orderitem.item.itemName}</p>
        </tr>
    </c:forEach>
    </tr>
<c:if test="${order.isPaymentAwaiting()}">
    <p><a href="<c:url value='/getuserorder/${clientLogin}'/>">Go to current order</a></p>
</c:if>
</c:forEach>

<p><a href="/paymentmethod">Complete order</a></p>
</body>
</html>
