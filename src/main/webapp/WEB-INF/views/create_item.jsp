<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new item</title>
</head>
<body>
<form action="/createitem" method="POST">
  Choose category: <select name="categoryId">
    <c:forEach var="cat" items="${listlowermostcategories}">
        <option value="${cat.categoryId}">${cat.categoryName}</option>
    </c:forEach>
</select><br>
    <p>Author: <input type = "text" name = "author"/></p>
    <p>Language: <input type = "text" name = "language" /></p>
    <p>Format: <input type = "text" name = "format" /></p>
    <p>Item name: <input type = "text" name = "itemName" /></p>
    <p>Item price: <input type = "text" name = "price" /></p>
    <p>Item weight: <input type = "text" name = "weight" /></p>
    <p>Item volume: <input type = "text" name = "volume" /></p>
    <p>Item availableCount: <input type = "text" name = "availableCount" /></p>
    <p>Item pic: <input type = "text" name = "pic" /></p>
    <p><input type = "submit" value = "Submit" /></p>
</form>
<p><a href="<c:url value='/backtoadminpage'/>">Main page</a></p>
</body>
</html>
