<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<a href="<c:url value='/getuserorder/${message}'/>">Show current order</a><br>
<a href="<c:url value='/getuserorders/${message}'/>">Show all current orders</a><br>
<a href="<c:url value='/logout'/>">Log out</a><br>
</body>
</html>
