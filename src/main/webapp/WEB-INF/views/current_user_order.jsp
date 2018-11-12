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
    <form action="/selectaddress" method="GET">
        Select address: <select name="address">
            <c:forEach var="address" items="${listAddresses}">
                <option value="${address.clientAddressId}">${address.country},${address.city},${address.street},${address.building},${address.apartment}</option>
            </c:forEach>
        </select>
        <input type="hidden" value="${currentorder.orderId}" name="orderId"/>
        <input type="submit">
    </form>
<p>
</c:if>
<p><a href="<c:url value='/backtomainpage'/>">Main page</a></p>
</body>
</html>
