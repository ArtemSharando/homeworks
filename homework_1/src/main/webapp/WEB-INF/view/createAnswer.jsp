<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html lang="${lang}">
<head>
    <title>Create Test</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/style/style.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"></head>
<body>
<form method="post">
    <h1>Answers</h1>
<c:forEach var = "question" items="${questions}"> <br><br> Question№${question.getCounterQuestion()}
    <c:forEach var = "i" begin = "1" end = "${question.getNumberOfAnswer()}">
        <c:out value="${i}"/>.<input type="text" name="variant${question.getCounterQuestion()}${i}">
        <input type="checkbox" name="correctAnswer${question.getCounterQuestion()}${i}" value="true">
    </c:forEach>
</c:forEach><br>
    <input type="submit" value="Complete">
</form>
</body>
</html>
