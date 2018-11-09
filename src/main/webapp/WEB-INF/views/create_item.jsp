<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alisa
  Date: 11/8/2018
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new item</title>
</head>
<body>
<h3>Lowermost categories</h3>
<%--<c:forEach var="cat" items="${listlowermostcategories}">--%>
    <%--<tr>--%>
            <%--${cat.categoryName}<br>--%>
    <%--</tr>--%>
<%--</c:forEach>--%>
<form action="/createitem" method="POST">
    <select name="categoryId">
    <c:forEach var="cat" items="${listlowermostcategories}">
        <option value="${cat.categoryId}">${cat.categoryName}</option>
    </c:forEach>
</select>
    Author: <input type = "text" name = "author"/>
    Language: <input type = "text" name = "language" />
    Format: <input type = "text" name = "format" />
    Item name: <input type = "text" name = "itemName" />
    Item price: <input type = "text" name = "price" />
    Item weight: <input type = "text" name = "weight" />
    Item volume: <input type = "text" name = "volume" />
    Item availableCount: <input type = "text" name = "availableCount" />
    Item pic: <input type = "text" name = "pic" />
    <input type = "submit" value = "Submit" />
</form>
</body>
</html>
