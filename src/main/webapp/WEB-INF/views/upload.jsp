<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload a picture</title>
</head>
<body>
<div class="container">
    <form:form method="POST" action="/uploadFile" enctype="multipart/form-data">
        <table>
            <tr>
                <td><input type="file" name="file"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="Submit"/></td>
            </tr>
        </table>
        </form>
    </form:form>
</div>
</body>
</html>