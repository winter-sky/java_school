<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alisa
  Date: 11/2/2018
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User cart</title>
</head>
<body>
<h3>Cart</h3>
<c:if test="${!empty user_cart}">
<c:forEach var="item" items="${user_cart}">
    <tr>
        <p><img src="${item.pic}" alt="some pic"></p>
        <p>Book name: ${item.itemName}</p>
        <a href="<c:url value='/addtoorder/${userlogin}/${item.itemId}'/>">Add to order</a><br>
    </tr>
</c:forEach>
</c:if>
<%--<p>${userlogin}</p>--%>
</body>
</html>
