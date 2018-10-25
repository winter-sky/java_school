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
<script>
    function changeContent(name) {
        $('#content').load(name);
    }
</script>
<!-- Compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <%--<a href="<c:url value='/catalog'/>" class="btn">Catalog</a><br>--%>
    <c:forEach var="rootCategory" items="${rootCategory.categories}">
        <%--<a href="<c:url value='/listsubcategories/${category.categoryId}' />"--%>
           <%--onclick="changeContent('hello.jsp')"> ${category.categoryName}</a><br>--%>
        <%--${rootCategory.categoryName}<br>--%>
        <a href="<c:url value='/showitemsbycategory/${rootCategory.categoryId}' />" >${rootCategory.categoryName}</a><br>
        <%--<a href="<c:url value='/showitems/${rootCategory.categoryId}' />" >${rootCategory.categoryName}</a><br>--%>
        <%--<c:if test="${!empty rootCategory.items}">--%>
        <%--<c:forEach var="item" items="${rootCategory.items}">--%>
            <%--<tr>--%>
                    <%--${item.itemName}--%>
            <%--</tr>--%>
        <%--</c:forEach></c:if>--%>
        <c:set var="rootCategory" value="${rootCategory}" scope="request"/>
        <jsp:include page="categories.jsp"/>

    </c:forEach>

    </div>
</body>
</html>
