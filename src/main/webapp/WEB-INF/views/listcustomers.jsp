<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 18.10.2018
  Time: 1:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>
    Add customer
</h1>
<form:form action="/addcustomer" modelAttribute="customers">
    <table>
        <c:if test="${!empty customers.firstName}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="customerId" readonly="true" size="8"  disabled="true" />
                    <form:hidden path="customerId" />
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="firstName">
                    <spring:message text="firstName"/>
                </form:label>
            </td>
            <td>
                <form:input path="firstName" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="lastName">
                    <spring:message text="lastName"/>
                </form:label>
            </td>
            <td>
                <form:input path="lastName" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="birthDate">
                    <spring:message text="birthDate"/>
                </form:label>
            </td>
            <td>
                <form:input path="birthDate" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="email">
                    <spring:message text="email"/>
                </form:label>
            </td>
            <td>
                <form:input path="email" />
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${empty customers.firstName}">
                    <input type="submit"
                           value="<spring:message text="Add customer"/>" />
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
<br>
<h3>Customers List</h3>
<c:forEach var="customers" items="${listCustomers}">
            <tr>
                <td>${customers.customerId}</td>
                <td>${customers.firstName}</td>
                <td>${customers.lastName}</td>
                <td>${customers.birthDate}</td>
                <td>${customers.email}</td>
            </tr>
        </c:forEach>
</body>
</html>
