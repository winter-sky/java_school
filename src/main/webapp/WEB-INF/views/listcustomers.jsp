<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 18.10.2018
  Time: 1:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Customers List</h3>
<c:forEach var="customers" items="${listCustomers}">
            <tr>
                <td>${customers.customerId}</td>
                <td>${customers.firstName}</td>
                <td>${customers.lastName}</td>
                <td>${customers.birthDate}</td>
                <td>${customers.email}</td>
            </tr>
        </c:forEach>
</body>
</html>
