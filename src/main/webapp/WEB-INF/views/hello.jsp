<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 10.10.2018
  Time: 1:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<html>
<head>
    <title>Hello Spring MVC</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            background-color: #f1f1f1;
            padding: 20px;
            text-align: center;
        }
        a {
            color: hotpink;
        }
    </style>
</head>
<body>
<h1>Hello, ${message}!</h1>
<a href="<c:url value='/searchclientbylogin/${message}'/>">Show profile</a><br>
<a href="<c:url value='/cart/usercart/${message}'/>">Cart</a><br>
<select>
    <c:forEach var="paymentItem" items="${payment}">
        <option value="${paymentItem.ordinal()}">${paymentItem.toString()}</option>
    </c:forEach>
</select>

<h3>Payment method</h3>
<c:forEach var="paymentItem" items="${payment}">
    <tr>
            ${paymentItem.toString()}<br>
                <a href="<c:url value='/selectpaymentmethod/${paymentItem}/${message}'/>">select</a><br>
    </tr>
</c:forEach>

</body>
</html>
