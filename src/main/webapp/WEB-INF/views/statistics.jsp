<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Statistics</title>
</head>
<body>
<a href="/adminpage">Back to admin page</a><br>
<h1>Statistics</h1>
<br/>
<a href="/topitems">Show top 10 items</a>
<br/>
<a href="/topclients">Show top 10 clients (by total amount)</a>
<br/>
<a href="/monthrevenue">Show revenue for current month</a>
<br/>
<a href="/weekrevenue">Show revenue for current week</a>
</body>
<p><a href="<c:url value='/backtoadminpage'/>">Main page</a></p>
</html>
