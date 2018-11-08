<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alisa
  Date: 11/6/2018
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment Method</title>
</head>
<body>
    <%--<select>--%>
        <%--<c:forEach var="paymentItem" items="${payment}">--%>
            <%--<option value="${paymentItem.ordinal()}">${paymentItem.toString()}</option>--%>
        <%--</c:forEach>--%>
    <%--</select>--%>

    <h3>Payment method</h3>
    <c:forEach var="paymentItem" items="${payment}">
    <tr>
            ${paymentItem.toString()}
        <a href="<c:url value='/selectpaymentmethod/${paymentItem}/${message}'/>">select</a><br>
    </tr>
    </c:forEach>
    <p><a href="/deliverymethod">Select delivery method</a></p>
</body>
</html>
