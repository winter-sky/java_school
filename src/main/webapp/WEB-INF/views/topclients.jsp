<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Verman
  Date: 09.11.2018
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Top clients</title>
</head>
<body>
<h1>Best clients (by total amount)</h1>
<table>
    <tr>
        <td>Client ID</td>
        <td>Last name</td>
        <td>First name</td>
        <td>Email</td>
        <td>Birth date</td>
        <td>Orders count</td>
        <td>Items count</td>
        <td>Total amount</td>
    </tr>
    <c:forEach var="item" items="${listClients}">
        <tr>
            <td>${item.getClient().getClientId()}</td>
            <td>${item.getClient().getLastName()}</td>
            <td>${item.getClient().getFirstName()}</td>
            <td>${item.getClient().getEmail()}</td>
            <td>${item.getClient().getBirthDate()}</td>
            <td>${item.getOrdersCount()}</td>
            <td>${item.getItemsCount()}</td>
            <td>${item.getAmount()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
