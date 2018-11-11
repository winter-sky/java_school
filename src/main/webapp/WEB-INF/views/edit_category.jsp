<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Category Page</title>
</head>
<body>
<h3>List All Categories</h3>
<c:forEach var="cat" items="${listallcategories}">
        Item ID: ${cat.categoryId}) ${cat.categoryName}
            <a href="<c:url value='/editcategory/${cat.categoryId}'/>">Edit</a>
        <br>
</c:forEach>
<form action="/updatecategory" method="POST">
    <p>Choose parent category: </p>
    <select name="parentId">
        <c:forEach var="cat" items="${listallparentcategories}">
            <option value="${cat.categoryId}">${cat.categoryName}</option>
        </c:forEach>
    </select>
    <br>
    <p><input type = "hidden" value="${category.categoryId}" name = "categoryId"/></p>
    <p>Category name: <input type = "text" value="${category.categoryName}" name = "categoryName"/></p>
    <input type = "submit" value = "Submit" />
</form>
</body>
</html>
