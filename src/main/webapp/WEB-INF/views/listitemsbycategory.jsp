<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <c:if test="${empty category.categories}">
        <c:forEach var="item" items="${category.items}">
            <tr>
                <p><img src="${item.pic}" alt="some pic" width="184" height="250"></p>
                    ${item.itemName}<br>
            </tr>
        </c:forEach>
    </c:if>
    <c:forEach var="category" items="${category.categories}">
        <c:forEach var="item" items="${category.items}">
            <p><img src="${item.pic}" alt="some pic" width="184" height="250"></p>
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
