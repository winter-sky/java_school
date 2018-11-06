<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alisa
  Date: 11/2/2018
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<html>
<head>
    <title>Guest cart</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            /*background-color: hsl(0, 0%, 90%);*/
            /*font-family: Arial;*/
            background-color: #f1f1f1;
            padding: 20px;
            /*text-align: center;*/
        }
    </style>
</head>
<body>
${sessionScope.guestcart.items}
<h3>Cart</h3>
<c:if test="${!empty guest_cart}">
<c:forEach var="item" items="${guest_cart}">
    <tr>
        <p><img src="${item.pic}" alt="some pic"></p>
        <p>Book name: ${item.itemName}</p>

    </tr>
</c:forEach>
</c:if>
</body>
</html>
