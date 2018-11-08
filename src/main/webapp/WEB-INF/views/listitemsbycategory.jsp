<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 21.10.2018
  Time: 5:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">--%>

<%--<!-- Compiled and minified JavaScript -->--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>--%>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <c:if test="${empty category.categories}">
        <c:forEach var="item" items="${category.items}">
            <tr>
                <p><img src="${item.pic}" alt="some pic"></p>
                    ${item.itemName}<br>
            </tr>
        </c:forEach>
    </c:if>
    <c:forEach var="category" items="${category.categories}">
        <c:forEach var="item" items="${category.items}">
            <p><img src="${item.pic}" alt="some pic"></p>
            ${item.itemName}<br>
     </c:forEach>
        <c:if test="${!empty category.categories}">
            <c:set var="category" value="${category}" scope="request"/>
            <jsp:include page="listitemsbycategory.jsp"/>
        </c:if>
    </c:forEach>
</div>
</body>
</html>
