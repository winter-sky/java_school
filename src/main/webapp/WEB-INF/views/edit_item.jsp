<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit item page</title>
</head>
<body>
<a href="/adminpage">Back to admin page</a><br>
<h3>List All Items</h3>
<c:forEach var="item" items="${listallItems}">
    <tr>
        <p>Item ID: ${item.itemId}) ${item.itemName}</p>
        <a href="<c:url value='/edititem/${item.itemId}'/>">Edit</a><br>
    </tr>
</c:forEach>
<form action="/updateitem" method="POST">
    <p>Choose category: </p>
    <select name="categoryId">
        <c:forEach var="cat" items="${listlowermostcategories}">
            <option value="${cat.categoryId}">${cat.categoryName}</option>
        </c:forEach>
    </select><br>
    <p><input type = "hidden" value="${item.itemId}" name = "itemId"/></p>
    <p>Author: <input type = "text" value="${item.params.author}" name = "author"/></p>
    <p>Language: <input type = "text" value="${item.params.language}" name = "language" /></p>
    <p>Format: <input type = "text" value="${item.params.format}" name = "format" /></p>
    <p>Item name: <input type = "text" value="${item.itemName}"  name = "itemName" /></p>
    <p>Item price: <input type = "text" value="${item.price}"  name = "price" /></p>
    <p>Item weight: <input type = "text" value="${item.weight}" name = "weight" /></p>
    <p>Item volume: <input type = "text" value="${item.volume}" name = "volume" /></p>
    <p>Item availableCount: <input type = "text" value="${item.availableCount}" name = "availableCount" /></p>
    <p>Item pic: <input type = "text" value="${item.pic}" name = "pic" /></p>
    <input type = "submit" value = "Submit" />
</form>
</body>
</html>
