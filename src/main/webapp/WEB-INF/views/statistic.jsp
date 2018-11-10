<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alisa
  Date: 11/10/2018
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Top 10 of Item</h3>
<c:forEach var="item" items="${topitems}">
    <tr>
            ${item.itemName}<br>
    </tr>
</c:forEach>
</body>
</html>
