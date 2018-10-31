<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 20.10.2018
  Time: 5:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>All books</h3>
<c:forEach var="items" items="${listItems}">
    <tr>
        <td><img src="${items.pic}" alt="some pic" style="width:50px;height:50px;"></td>
        <td>${items.itemName}</td><br><br>
    </tr>
</c:forEach>
</body>
</html>
