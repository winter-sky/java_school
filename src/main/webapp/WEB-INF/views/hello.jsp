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
            /*background-color: hsl(0, 0%, 90%);*/
            /*font-family: Arial;*/
            /*padding: 20px;*/
            background-color: #f1f1f1;
            padding: 20px;
            text-align: center;
        }
        /*a:link {*/
            /*text-decoration: none;*/
        /*}*/
        a {
            color: hotpink;
        }
    </style>
</head>
<body>
<h1>Hello, ${message}!</h1>
<a href="<c:url value='/searchclientbylogin/${message}'/>">Show profile</a><br>
</body>
</html>
