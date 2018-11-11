<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>List items</h3>
<c:forEach var="item" items="${listItems}">
    <tr>

            ${item.itemName}<br>
    </tr>
</c:forEach>
<h3>Language</h3>
<c:forEach var="Language" items="${listLanguages}">
    <tr>
            ${Language}<br>
    </tr>
</c:forEach>

<h3>Format</h3>
<c:forEach var="Format" items="${listFormats}">
    <tr>
            ${Format}<br>
    </tr>
</c:forEach>
</body>
</html>
