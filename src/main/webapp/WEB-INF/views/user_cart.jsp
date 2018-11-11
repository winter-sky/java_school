<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <p><img src="${item.pic}" alt="some pic" width="184" height="250"></p>
        <p>Book name: ${item.itemName}</p>
        <a href="<c:url value='/addtoorder/${userlogin}'/>">Add to order</a><br>
    </tr>
</c:forEach>
</c:if>
</body>
</html>
