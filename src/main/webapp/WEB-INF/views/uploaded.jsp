<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload a picture</title>
</head>
<body>
<h2>Submitted File</h2>

<table>
    <tr>
        <td>OriginalFileName:</td>
        <td>${file.originalFilename}</td>
    </tr>
    <tr>
        <td>Type:</td>
        <td>${file.contentType}</td>
    </tr>
    <tr>
        <td>Path:</td>
        <td>${path}</td>
    </tr>
    <tr>
        <td>Error:</td>
        <td>${error}</td>
    </tr>
    <tr>
        <td><img src="${url}" alt="Uploaded"></td>
    </tr>
</table>
</body>
</html>