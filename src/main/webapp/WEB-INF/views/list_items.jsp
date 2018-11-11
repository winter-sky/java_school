<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
        <p><img src="${items.pic}" alt="some pic" width="184" height="250"></p>
        <p>Book name: ${items.itemName}</p>
        <security:authorize access="hasRole('USER')">
            <a href="<c:url value='/cart/additemtousercart/${items.itemId}/${sessionScope.initialusercart.cartId}'/>">Add to user cart</a><br>
        </security:authorize>
        <c:if test="${empty checkprincipal}">
        <a href="<c:url value='/cart/additem/${items.itemId}/${sessionScope.guestcart.cartId}'/>">Add to guest cart</a><br>
        </c:if>
    </tr>
</c:forEach>
<security:authorize access="hasRole('USER')">
    <p>check</p>
</security:authorize>
</body>
</html>
