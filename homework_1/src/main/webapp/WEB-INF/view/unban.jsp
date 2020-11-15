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
<form method="post">
    <select name="user" id="user_ban">
        <c:forEach var="user" items="${users}">
            <option value="${user.getId()}">
                <c:out value="${user.getUsername()}"/>
            </option>
        </c:forEach>
    </select>
    <input type="submit" value="Unban">
</form>
</body>
</html>
