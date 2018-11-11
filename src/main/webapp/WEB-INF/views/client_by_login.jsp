<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<html>
<head>
    <title>Profile</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        .center {
            margin: auto;
            width: 60%;
            //border: 3px solid #73AD21;
            padding: 10px;
        }
        body {
            background-color: #f1f1f1;
            padding: 20px;
        }
        a {
            color: hotpink;
        }
    </style>
</head>
<body>
<div class="center" >
    <h3>Profile info: </h3>
    <p>First name: ${client.firstName}</p>
    <p>Last name: ${client.lastName}</p>
    <p>Birth date: ${client.birthDate}</p>
    <p>email: ${client.email}</p>
    <p>Address: ${client.clientAddress.country}, ${client.clientAddress.zipCode}, ${client.clientAddress.city}, ${client.clientAddress.street}, ${client.clientAddress.building}-${client.clientAddress.apartment}</p>
    <p><a href="<c:url value='/edit/${client.clientId}'/>">Edit profile</a></p>
</div>
</body>
</html>
