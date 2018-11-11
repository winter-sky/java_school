<%--
  Created by IntelliJ IDEA.
  User: Leonid
  Date: 11.11.2018
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Revenue statistics</title>
</head>
<body>
<h1>Revenue statistics</h1>
<table>
    <tr>
        <td>Start date</td>
        <td>Orders</td>
        <td>Items</td>
        <td>Revenue</td>
    </tr>
    <tr>
        <td>${revenueStat.getStartDate()}</td>
        <td>${revenueStat.getOrders()}</td>
        <td>${revenueStat.getItems()}</td>
        <td>${revenueStat.getRevenue()}</td>
    </tr>
</table>
</body>
</html>
