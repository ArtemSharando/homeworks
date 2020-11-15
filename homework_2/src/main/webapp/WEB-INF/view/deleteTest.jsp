<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="../jspf/directive/lang.jspf" %>

<html lang="${lang}">
<head>
    <title>Delete Test</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/style/style.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"></head>
<body>
<form name="deleteTest" method="post">
    <select name="test" id="test">
    <c:forEach var="test" items="${tests}">
        <option value="${test.getId()}">
            <c:out value="${test.getName()}"/>
        </option>
    </c:forEach>
    </select>
    <br>
    <input type="submit" value="Delete">
</form>
</body>
</html>
