<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 21.10.2018
  Time: 3:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!DOCTYPE html>
<html lang="en">
<head>
    <title>CSS Website Layout</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
        }

        /* Style the header */
        .header {
            background-color: #f1f1f1;
            padding: 20px;
            text-align: center;
        }

        /* Style the top navigation bar */
        .topnav {
            overflow: hidden;
            background-color: #333;
        }

        /* Style the topnav links */
        .topnav a {
            float: left;
            display: block;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        /* Change color on hover */
        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        /* Create three unequal columns that floats next to each other */
        .column {
            float: left;
            padding: 10px;
        }

        /* Left and right column */
        .column.side {
            width: 25%;
        }

        /* Middle column */
        .column.middle {
            width: 50%;
        }

        /* Clear floats after the columns */
        .row:after {
            content: "";
            display: table;
            clear: both;
        }

        /* Responsive layout - makes the three columns stack on top of each other instead of next to each other */
        @media screen and (max-width: 600px) {
            .column.side, .column.middle {
                width: 100%;
            }
        }

        /* Style the footer */
        .footer {
            background-color: #f1f1f1;
            padding: 10px;
            text-align: center;
        }
        .topnav.right {
            position: absolute;
            right: 0px;
        }
    </style>
</head>
<body>
<div class="header">
    <h1>Tir Na Nog Book Store</h1>
</div>

<div class="topnav">
    <a href="#">Link</a>
    <a href="#">Link</a>
    <a href="#">Link</a>
    <div class="topnav right">
    <a href="/hello">Log in</a>
        <a href="#">Cart</a>
    </div>
</div>

<div class="row">
    <div class="column side">
        <div class="w3-panel w3-pink">
            <h2 class="w3-opacity">Filter</h2>
        </div>
        <p><b>Categories</b></p>
        <div class="container">
            <c:forEach var="rootCategory" items="${rootCategory.categories}">
                <%--<a href="<c:url value='/listsubcategories/${category.categoryId}' />"--%>
                <%--onclick="changeContent('hello.jsp')"> ${category.categoryName}</a><br>--%>
                <a href="<c:url value='/showitemsbycategory/${rootCategory.categoryId}' />" >${rootCategory.categoryName}</a><br>
                <c:set var="rootCategory" value="${rootCategory}" scope="request"/>
                <jsp:include page="categories.jsp"/>
            </c:forEach>

            <p><b>Author</b></p>
            <c:forEach var="author" items="${listAuthors}">
                <tr>
                        <%--${author.author}<br>--%>
                    <a href="<c:url value='/searchbyauthor/${author}'/>">${author}</a><br>
                </tr>
            </c:forEach>

            <p><b>Language</b></p>
            <c:forEach var="Language" items="${listLanguages}">
                <tr>
                        ${Language}<br>
                </tr>
            </c:forEach>

            <p><b>Format</b></p>
            <c:forEach var="Format" items="${listFormats}">
                <tr>
                        ${Format}<br>
                </tr>
            </c:forEach>
        </div>
    </div>
    <div class="column middle">
        <h2>Books will be here</h2>
        <div class="container">
            <c:if test="${empty category.categories}">
                <c:forEach var="item" items="${category.items}">
                    <tr>
                            ${item.itemName}<br>
                    </tr>
                </c:forEach>
            </c:if>
            <c:forEach var="category" items="${category.categories}">
                <c:forEach var="item" items="${category.items}">
                    ${item.itemName}<br>
                </c:forEach>
                <c:if test="${!empty category.categories}">
                    <c:set var="category" value="${category}" scope="request"/>
                    <jsp:include page="listitemsbycategory.jsp"/>
                </c:if>
            </c:forEach>
        </div>
        <div class="container">
            <c:forEach var="item" items="${listItems}">
                <tr>
                        ${item.itemName}<br>
                </tr>
            </c:forEach>
        </div>

    </div>
    <div class="column side">
        <h2>Something another</h2>
        <p>content</p>
    </div>
</div>

<div class="footer">
    <p>Footer</p>
</div>

</body>
</html>

