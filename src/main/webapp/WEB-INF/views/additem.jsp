<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Verman
  Date: 08.11.2018
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add item</title>
</head>
<body>
<h1>
    Add an item.
</h1>
<form:form action="/additem" modelAttribute="item">
    <table>
        <tr>
            <td>
                <form:label path="itemName">
                    <spring:message text="Item name"/>
                </form:label>
            </td>
            <td>
                <form:input path="itemName" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="price">
                    <spring:message text="Price"/>
                </form:label>
            </td>
            <td>
                <form:input path="price" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="weight">
                    <spring:message text="Weight"/>
                </form:label>
            </td>
            <td>
                <form:input path="weight" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="volume">
                    <spring:message text="Volume"/>
                </form:label>
            </td>
            <td>
                <form:input path="volume" />
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${empty item.itemName}">
                    <input type="submit"
                           value="<spring:message text="Add item"/>" />
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
<br>
</body>
</html>
