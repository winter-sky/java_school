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
<head>
    <title>Title</title>
</head>
<body>

<%--<c:if test="${empty category.categories}">--%>
    <c:forEach var="item" items="${items}">
        <tr>
                ${item.itemName}<br>
                    <%--<a href="<c:url value='/listauthors/${item.category.categoryId}'/>">${item.itemName}</a><br>--%>
        </tr>
    </c:forEach>
<%--</c:if>--%>


<c:forEach var="category" items="${category.categories}">
     <c:forEach var="item" items="${category.items}">
            ${item.itemName}<br>
         <%--<a href="<c:url value='/listauthors/${item.category.categoryId}'/>">${item.itemName}</a><br>--%>
    <c:if test="${!empty category.categories}">
    <c:set var="category" value="${category}" scope="request"/>
    <jsp:include page="listitemsbycategory.jsp"/>
    </c:if>
</c:forEach>
</c:forEach>
<h3>оп</h3>
</body>
</html>
