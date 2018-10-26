<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 26.10.2018
  Time: 3:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>Author</h3>
<c:forEach var="author" items="${listAuthors}">
    <tr>
            <%--${author.author}<br>--%>
                <a href="<c:url value='/searchbyauthor/${author}'/>">${author}</a><br>
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


