<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alisa
  Date: 11/8/2018
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delivery method</title>
</head>
<body>
<h3>Select delivery method</h3>
<c:forEach var="deliveryItem" items="${delivery}">
    <tr>
            ${deliveryItem.toString()}
        <a href="<c:url value='/selectdeliverymethod/${deliveryItem}/${message}'/>">select</a><br>
    </tr>
</c:forEach>
</body>
</html>
