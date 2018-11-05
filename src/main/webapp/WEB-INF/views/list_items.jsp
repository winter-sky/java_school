<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 20.10.2018
  Time: 5:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>All books</h3>
<c:forEach var="items" items="${listItems}">
    <tr>
        <td><img src="${items.pic}" alt="some pic"></td>
        <td>${items.itemName}</td><br><br>
        <c:if test="${!empty checkprincipal}">
            <a href="<c:url value='/cart/additemtousercart/${items.itemId}/${sessionScope.initialusercart.cartId}'/>">Add to cart</a><br>
        </c:if>
        <c:if test="${empty checkprincipal}">
        <a href="<c:url value='/cart/additem/${items.itemId}/${sessionScope.guestcart.cartId}'/>">Add to cart</a><br>
        </c:if>
    </tr>
</c:forEach>

</body>
</html>
