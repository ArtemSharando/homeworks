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
<c:if test="${isAdmin != null}">
    <a href="userAdmin/ban"><fmt:message key="userAdmin_jsp.ban"/></a> |
</c:if><c:if test="${isAdmin != null}">
    <a href="userAdmin/unban"><fmt:message key="userAdmin_jsp.unban"/></a> |
</c:if><c:if test="${isAdmin != null}">
    <a href="userAdmin/edit"><fmt:message key="userAdmin_jsp.edit_user"/></a> |
</c:if>
</body>
</html>
