<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Tir Na Nog Book Store</title>
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
            padding: 20px 20px;
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
            padding: 0px;
        }
    </style>
</head>
<body>
<security:authorize access="hasRole('ADMIN')">
<h3>Item and categories management</h3>
<a href="/edititempage">Edit items</a><br>
<a href="/createnewitem"> Create new item</a><br>
<a href="/editcategorypage">Edit categories</a><br>
<a href="/createcategorypage">Create new category</a><br>
<h3>View and manage orders</h3>
<a href="/getallorders">Show all orders</a><br>
<h3>Statistics</h3>
<a href="/topitems">Show top 10 items</a>
<br/>
<a href="/topclients">Show top 10 clients (by total amount)</a>
<br/>
<a href="/monthrevenue">Show revenue for current month</a>
<br/>
<a href="/weekrevenue">Show revenue for current week</a>
</body>
</security:authorize>
<security:authorize access="hasRole('USER')">
    <div class="header">
        <h1>Tir Na Nog Book Store</h1>
        <c:if test="${empty checkprincipal}">
            <a href="/cart/guestcart">Cart <img src="https://cdn4.iconfinder.com/data/icons/shopping-21/64/shopping-06-512.png" alt="some pic" width="25" height="25"></a>
        </c:if>
        <c:if test="${!empty checkprincipal}">
            <security:authorize access="hasRole('USER')">
                <a href="<c:url value='/cart/usercart/${userLogin}'/>">Cart <img src="https://cdn4.iconfinder.com/data/icons/shopping-21/64/shopping-06-512.png" alt="some pic" width="25" height="25"></a>
            </security:authorize>
        </c:if>
    </div>

    <div class="topnav">
        <a href="itemlist">Show all books</a>
        <div class="topnav right">
            <c:if test="${empty checkprincipal}">
                <%--<a href="/cart/guestcart">Cart <img src="https://cdn4.iconfinder.com/data/icons/shopping-21/64/shopping-06-512.png" alt="some pic" width="20" height="20"></a>--%>
                <a href="/login">Log in</a>
            </c:if>
            <c:if test="${!empty checkprincipal}">
                <security:authorize access="hasRole('USER')">
                    <%--<a href="<c:url value='/cart/usercart/${userLogin}'/>">Cart</a>--%>
                    <a href="<c:url value='/getuserorders/${userLogin}'/>">Orders</a>
                    <a href="<c:url value='/searchclientbylogin/${userLogin}'/>">Show profile</a>
                </security:authorize>
                <a href="<c:url value='/logout'/>">Log out</a>
            </c:if>
            </a>
        </div>
    </div>

    <div class="row">
        <div class="column side">
            <p><b>Categories</b></p>
            <div class="container">
                <c:forEach var="rootCategory" items="${rootCategory.categories}">
                    <a href="<c:url value='/showitemsbycategory/${rootCategory.categoryId}' />" >${rootCategory.categoryName}</a><br>
                    <c:set var="rootCategory" value="${rootCategory}" scope="request"/>
                    <jsp:include page="categories.jsp"/>
                </c:forEach>

                <p><b>Author</b></p>
                <c:forEach var="author" items="${listAuthors}">
                    <tr>
                        <a href="<c:url value='/searchbyauthor/${author}'/>">${author}</a><br>
                    </tr>
                </c:forEach>

                <p><b>Language</b></p>
                <c:forEach var="language" items="${listLanguages}">
                    <tr>
                        <a href="<c:url value='/searchbylanguage/${language}'/>">${language}</a><br>
                    </tr>
                </c:forEach>

                <p><b>Format</b></p>
                <c:forEach var="format" items="${listFormats}">
                    <tr>
                        <a href="<c:url value='/searchbyformat/${format}'/>">${format}</a><br>
                    </tr>
                </c:forEach>
            </div>
        </div>
        <div class="column middle">
            <c:forEach var="items" items="${listItems}">
                <tr>
                    <p><img src="${items.pic}" alt="some pic" width="184" height="250"></p>
                    <p>Book name: ${items.itemName}</p>
                    <p>Price: ${items.price}</p>
                    <security:authorize access="hasRole('USER')">
                        <a href="<c:url value='/cart/additemtousercart/${items.itemId}/${sessionScope.initialusercart.cartId}'/>">Add to user cart</a><br>
                    </security:authorize>
                    <c:if test="${empty checkprincipal}">
                        <a href="<c:url value='/cart/additem/${items.itemId}/${sessionScope.guestcart.cartId}'/>">Add to guest cart</a><br>
                    </c:if>
                </tr>
            </c:forEach>

            <c:forEach var="items" items="${showallitems}">
                <tr>
                    <p><img src="${items.pic}" alt="some pic" alt="some pic" width="184" height="250"></p>
                        <%--<p>Book id: ${items.itemId} </p>--%>
                    <p>Book name: ${items.itemName}</p>
                    <p>Price: ${items.price}</p>
                    <security:authorize access="hasRole('USER')">
                        <a href="<c:url value='/cart/additemtousercart/${items.itemId}/${sessionScope.initialusercart.cartId}'/>">Add to user cart</a><br>
                    </security:authorize>
                    <c:if test="${empty checkprincipal}">
                        <a href="<c:url value='/cart/additem/${items.itemId}/${sessionScope.guestcart.cartId}'/>">Add to guest cart</a><br>
                    </c:if>
                </tr>
            </c:forEach>

            <div class="container">
                <c:if test="${empty category.categories}">
                    <c:forEach var="item" items="${category.items}">
                        <tr>
                            <p><img src="${item.pic}" alt="some pic" alt="some pic" width="184" height="250"></p>
                                ${item.itemName}<br>

                        </tr>
                    </c:forEach>
                </c:if>
                <c:forEach var="category" items="${category.categories}">
                    <c:forEach var="item" items="${category.items}">
                        <p><img src="${item.pic}" alt="some pic" alt="some pic" width="184" height="250"></p>
                        ${item.itemName}

                    </c:forEach>
                    <c:if test="${!empty category.categories}">
                        <c:set var="category" value="${category}" scope="request"/>
                        <jsp:include page="listitemsbycategory.jsp"/>
                    </c:if>
                </c:forEach>
            </div>
            <div class="container">

            </div>

        </div>
        <div class="column side">

        </div>
    </div>

    <div class="footer">
        <p>Footer</p>
    </div>
</security:authorize>
<c:if test="${empty checkprincipal}">
<div class="header">
    <h1>Tir Na Nog Book Store</h1>
    <c:if test="${empty checkprincipal}">
        <a href="/cart/guestcart">Cart <img src="https://cdn4.iconfinder.com/data/icons/shopping-21/64/shopping-06-512.png" alt="some pic" width="25" height="25"></a>
    </c:if>
    <c:if test="${!empty checkprincipal}">
        <security:authorize access="hasRole('USER')">
            <a href="<c:url value='/cart/usercart/${userLogin}'/>">Cart <img src="https://cdn4.iconfinder.com/data/icons/shopping-21/64/shopping-06-512.png" alt="some pic" width="25" height="25"></a>
        </security:authorize>
    </c:if>
</div>

    <div class="topnav">
        <a href="itemlist">Show all books</a>
        <div class="topnav right">
            <c:if test="${empty checkprincipal}">
                <%--<a href="/cart/guestcart">Cart <img src="https://cdn4.iconfinder.com/data/icons/shopping-21/64/shopping-06-512.png" alt="some pic" width="20" height="20"></a>--%>
                <a href="/login">Log in</a>
            </c:if>
            <c:if test="${!empty checkprincipal}">
                <security:authorize access="hasRole('USER')">
                    <%--<a href="<c:url value='/cart/usercart/${userLogin}'/>">Cart</a>--%>
                    <a href="<c:url value='/getuserorders/${userLogin}'/>">Orders</a>
                    <a href="<c:url value='/searchclientbylogin/${userLogin}'/>">Show profile</a>
                </security:authorize>
                <a href="<c:url value='/logout'/>">Log out</a>
            </c:if>
            </a>
        </div>
    </div>

    <div class="row">
        <div class="column side">
            <p><b>Categories</b></p>
            <div class="container">
                <c:forEach var="rootCategory" items="${rootCategory.categories}">
                    <a href="<c:url value='/showitemsbycategory/${rootCategory.categoryId}' />" >${rootCategory.categoryName}</a><br>
                    <c:set var="rootCategory" value="${rootCategory}" scope="request"/>
                    <jsp:include page="categories.jsp"/>
                </c:forEach>

                <p><b>Author</b></p>
                <c:forEach var="author" items="${listAuthors}">
                    <tr>
                        <a href="<c:url value='/searchbyauthor/${author}'/>">${author}</a><br>
                    </tr>
                </c:forEach>

                <p><b>Language</b></p>
                <c:forEach var="language" items="${listLanguages}">
                    <tr>
                        <a href="<c:url value='/searchbylanguage/${language}'/>">${language}</a><br>
                    </tr>
                </c:forEach>

                <p><b>Format</b></p>
                <c:forEach var="format" items="${listFormats}">
                    <tr>
                        <a href="<c:url value='/searchbyformat/${format}'/>">${format}</a><br>
                    </tr>
                </c:forEach>
            </div>
        </div>
        <div class="column middle">
            <c:forEach var="items" items="${listItems}">
                <tr>
                    <p><img src="${items.pic}" alt="some pic" width="184" height="250"></p>
                    <p>Book name: ${items.itemName}</p>
                    <p>Price: ${items.price}</p>
                    <security:authorize access="hasRole('USER')">
                        <a href="<c:url value='/cart/additemtousercart/${items.itemId}/${sessionScope.initialusercart.cartId}'/>">Add to user cart</a><br>
                    </security:authorize>
                    <c:if test="${empty checkprincipal}">
                        <a href="<c:url value='/cart/additem/${items.itemId}/${sessionScope.guestcart.cartId}'/>">Add to guest cart</a><br>
                    </c:if>
                </tr>
            </c:forEach>

            <c:forEach var="items" items="${showallitems}">
                <tr>
                    <p><img src="${items.pic}" alt="some pic" alt="some pic" width="184" height="250"></p>
                        <%--<p>Book id: ${items.itemId} </p>--%>
                    <p>Book name: ${items.itemName}</p>
                    <p>Price: ${items.price}</p>
                    <security:authorize access="hasRole('USER')">
                        <a href="<c:url value='/cart/additemtousercart/${items.itemId}/${sessionScope.initialusercart.cartId}'/>">Add to user cart</a><br>
                    </security:authorize>
                    <c:if test="${empty checkprincipal}">
                        <a href="<c:url value='/cart/additem/${items.itemId}/${sessionScope.guestcart.cartId}'/>">Add to guest cart</a><br>
                    </c:if>
                </tr>
            </c:forEach>

            <div class="container">
                <c:if test="${empty category.categories}">
                    <c:forEach var="item" items="${category.items}">
                        <tr>
                            <p><img src="${item.pic}" alt="some pic" alt="some pic" width="184" height="250"></p>
                                ${item.itemName}<br>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:forEach var="category" items="${category.categories}">
                    <c:forEach var="item" items="${category.items}">
                        <p><img src="${item.pic}" alt="some pic" alt="some pic" width="184" height="250"></p>
                        ${item.itemName}
                    </c:forEach>
                    <c:if test="${!empty category.categories}">
                        <c:set var="category" value="${category}" scope="request"/>
                        <jsp:include page="listitemsbycategory.jsp"/>
                    </c:if>
                </c:forEach>
            </div>
            <div class="container">

            </div>

        </div>
        <div class="column side">

        </div>
    </div>

    <div class="footer">
        <p>Footer</p>
    </div>
</c:if>
</body>
</html>

