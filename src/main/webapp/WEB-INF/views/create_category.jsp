<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create category</title>
</head>
<body>
<%--<h3>All categories</h3>--%>
<%--<c:forEach var="cat" items="${listallcategories}">--%>
<%--<tr>--%>
<%--${cat.categoryName}<br>--%>
<%--</tr>--%>
<%--</c:forEach>--%>
<p>Choose parent category</p>
<form action="/addnewcategory" method="POST">
    <select name="categoryId">
        <c:forEach var="cat" items="${listallparentcategories}">
            <option value="${cat.categoryId}">${cat.categoryName}</option>
        </c:forEach>
    </select><br>
    <p>Create new category</p>
    <p>New category name: <input type = "text" name = "categoryName"/></p>
    <p>New category level: <input type = "text" name = "categoryLevel"/></p>
    <input type = "submit" value = "Submit" />
</form>
<p><a href="<c:url value='/backtoadminpage'/>">Main page</a></p>
</body>
</html>
