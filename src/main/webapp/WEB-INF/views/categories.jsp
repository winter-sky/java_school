<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
            <c:forEach var="rootCategory" items="${rootCategory.categories}">
                <a href="<c:url value='/showitemsbycategory/${rootCategory.categoryId}' />" >${rootCategory.categoryName}</a><br>
                <c:set var="rootCategory" value="${rootCategory}" scope="request"/>
                <jsp:include page="categories.jsp"/>
            </c:forEach>
        </div>
</body>
</html>
