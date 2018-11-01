<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Alisa
  Date: 10/31/2018
  Time: 5:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="/updateclient" modelAttribute="client">
    <table>
            <tr>
                <%--<td>--%>
                    <%--<form:label path="clientId">--%>
                        <%--<spring:message text="ID"/>--%>
                    <%--</form:label>--%>
                <%--</td>--%>
                <td>
                    <%--<form:input path="clientId" readonly="true" size="8"  disabled="true" />--%>
                    <form:hidden path="clientId" />
                </td>
            </tr>
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
                <form:input path="birthDate" placeholder="yyyy-mm-dd" />
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
            <td>
                <form:label path="clientAddress.country">
                    <spring:message text="country"/>
                </form:label>
            </td>
            <td>
                <form:input path="clientAddress.country" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="clientAddress.city">
                    <spring:message text="city"/>
                </form:label>
            </td>
            <td>
                <form:input path="clientAddress.city" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="clientAddress.zipCode">
                    <spring:message text="zip code"/>
                </form:label>
            </td>
            <td>
                <form:input path="clientAddress.zipCode" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="clientAddress.street">
                    <spring:message text="street"/>
                </form:label>
            </td>
            <td>
                <form:input path="clientAddress.street" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="clientAddress.building">
                    <spring:message text="building"/>
                </form:label>
            </td>
            <td>
                <form:input path="clientAddress.building" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="clientAddress.apartment">
                    <spring:message text="apartment"/>
                </form:label>
            </td>
            <td>
                <form:input path="clientAddress.apartment" />
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty client.firstName}">
                    <input type="submit"
                           value="<spring:message text="Edit client"/>" />
                </c:if>
                <c:if test="${empty client.firstName}">
                    <input type="submit"
                           value="<spring:message text="Add client"/>" />
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
<h3>Client List</h3>
<c:if test="${!empty listclients}">
    <table class="tg">
        <c:forEach items="${listclients}" var="client">
            <tr>
                <%--<td>${client.clientId}</td>--%>
                <td>${client.firstName}</td>
                <td>${client.lastName}</td>
                    <td><a href="<c:url value='/edit/${client.clientId}'/>">Edit</a><td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
