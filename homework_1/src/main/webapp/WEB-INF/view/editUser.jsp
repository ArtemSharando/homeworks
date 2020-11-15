<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="../jspf/directive/lang.jspf" %>
<html lang="${lang}">
<head>
    <title>User</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/style/style.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="../../resources/script.js"></script>
</head>
<body>
<form method="post">
    <input class="form-control" type="text" placeholder="Find user" id="myInput" onkeyup="filtr(0)">
<table class="table table-striped" id="info-table">
    <tr>
        <th>
            Username
        </th>
        <th>
            Password
        </th>
        <th>
            Name
        </th>
        <th>
            Surname
        </th>
    </tr>
<c:forEach var="user" items="${users}">
<tr>
    <td>
    <input type="text" value="${user.getUsername()}" name="username${user.getId()}">
    </td>
    <td>
    <input type="text" value="${user.getPassword()}" name="password${user.getId()}">
    </td>
    <td>
    <input type="text" value="${user.getName()}" name="name${user.getId()}">
    </td>
    <td>
     <input type="text" value="${user.getSurname()}" name="surname${user.getId()}">
    </td>
</tr>
</c:forEach>
</table>
<input type="submit" value="Update">
</form>
</body>
</html>
