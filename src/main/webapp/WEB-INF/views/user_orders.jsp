<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Current orders</title>
</head>
<body>
<h3>Order history</h3>
<c:forEach var="order" items="${orderlist}">
    <tr>
    <p>Order Id: ${order.orderId}<br></p>
    <p>Full price: ${order.orderPrice}<br></p>
    <p>Order Status: ${order.orderStatus.toString()}<br></p>
    <c:forEach var="orderitem" items="${order.orderItems}">
        <tr>
            <p><img src="${orderitem.item.pic}" alt="some pic" width="184" height="250"></p>
            <p>Book name: ${orderitem.item.itemName}</p>
            <p>Author: ${orderitem.item.params.author}</p>
            <p>Language: ${orderitem.item.params.language}</p>
            <p>Format: ${orderitem.item.params.format}</p>
            <p>Quantity: ${orderitem.itemQuantity}</p>
        </tr>
    </c:forEach>
    </tr>
<c:if test="${order.isPaymentAwaiting()}">
    <p><a href="<c:url value='/getuserorder/${clientLogin}'/>">Complete order</a></p>
</c:if>
</c:forEach>
<p><a href="<c:url value='/backtomainpage'/>">Main page</a></p>
</body>
</html>
