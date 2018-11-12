<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>All client orders</h3>

<table style="width:100%">
    <tr>
        <th>Client</th>
        <th>Order Status</th>
        <th>Payment Status</th>
        <th>Items</th>
        <th>Full price</th>
        <th>Edit order status</th>
    </tr>
    <c:forEach var="order" items="${listallorders}">
        <tr>
            <td>${order.client.firstName} ${order.client.lastName}</td>
            <td>${order.orderStatus.toString()}</td>
            <td>${order.paymentStatus.toString()}</td>
            <td>
                <select><c:forEach var="orderitem" items="${order.orderItems}">
                <option value="${orderitem.item.itemName}">${orderitem.item.itemName}</option></c:forEach>
            </select>
            </td>
            <td>${order.orderPrice}</td>
            <td><form action="/selectorderstatus" method="GET">
                <select name="status">
                    <c:forEach var="status" items="${orderstatus}">
                        <option value="${status.ordinal()}">${status.toString()}</option>
                    </c:forEach>
                </select>
                <input type="hidden" value="${order.orderId}" name="orderId"/>
                <input type="submit">
            </form>
            </td>
        </c:forEach>
</table>
<p><a href="<c:url value='/backtoadminpage'/>">Main page</a></p>
</body>
</html>
