<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="../jspf/directive/lang.jspf" %>

<html lang="${lang}">
<head>
    <title>User</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/style/style.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<table class="ban-user" id="table-ban">
    <tr>
        <th>
            Username
        </th>
    </tr>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>
                <input type="hidden" value="${user.getUsername()}">
            <c:out value="${user.getUsername()}"/>
            </td>
            <form method="post">
                <td>
                <input type="hidden" name="userId" value="${user.getId()}">
                <input type="submit"  value="Ban">
                </td>
            </form>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
