<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 21.10.2018
  Time: 3:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="<c:url value='/catalog' />" >Catalog</a><br>
<c:forEach var="category" items="${listCategories}">
        <a href="<c:url value='/listsubcategories/${category.categoryId}' />" > ${category.categoryName}</a><br>

</c:forEach>
</body>
</html>
