<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="../jspf/directive/lang.jspf" %>

<html lang="${lang}">
<head>
    <title>Menu</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/style/style.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<c:if test="${isLogged != null}">
    <a href="testAdmin/createTest"><fmt:message key="testAdmin_jsp.list.create_test"/></a> |
</c:if>
<c:if test="${isLogged != null}">
    <a href="testAdmin/deleteTest"><fmt:message key="testAdmin_jsp.list.delete_test"/></a> |
</c:if>
</body>
</html>