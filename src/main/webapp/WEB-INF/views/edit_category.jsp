<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alisa
  Date: 11/9/2018
  Time: 3:42
  To change this template use File | Settings | File Templates.
--%>
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
    <%--<p>Category level: <input type = "text" value="${category.categoryLevel}" name = "categoryLevel" /></p>--%>
    <input type = "submit" value = "Submit" />
</form>
</body>
</html>
