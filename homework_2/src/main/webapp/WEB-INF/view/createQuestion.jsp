<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="../jspf/directive/lang.jspf" %>

<html lang="${lang}">
<head>
    <title>Create Question</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/style/style.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"></head>
<body>
<form id="create_question"  method="post">
<c:forEach var = "i" begin = "1" end = "${numberQ}">
    Question content: <br>
    <c:out value="${i}"/>. <input type="text" name="content${i}"><br>
    Number of answer:<input name="numberA${i}" type="number" min="1" max="6" value="1"> <br><br>
</c:forEach>
    <input type="submit" value="Next">
</form>
</body>
</html>
