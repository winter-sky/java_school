<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment Method</title>
</head>
<body>

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
