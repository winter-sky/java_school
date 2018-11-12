<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alisa
  Date: 11/12/2018
  Time: 3:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/selectaddress" method="GET">
    <select name="address">
        <c:forEach var="address" items="${client.clientAddresses}">
            <option value="${address}">${address.country}</option>
        </c:forEach>
    </select>
    <input type="submit">
</form>


</body>
</html>
