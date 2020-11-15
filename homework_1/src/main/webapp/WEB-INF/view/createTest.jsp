<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="../jspf/directive/lang.jspf" %>

<html lang="${lang}">
<head>
    <title>Create Test</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/style/style.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"></head>
<body>
<form  id="create_test"  method="post">
    Test Name:<input type="text" name="name"><br>
    Subject: <select name="subject" id="subject">
    <c:forEach var="subject" items="${subjects}">
        <option value="${subject.getId()}">
            <c:out value="${subject.getName()}"/>
        </option>
    </c:forEach>
</select>
    <br>
    Complexity:<select name="complexity" id="complexity">
    <c:forEach var="complexity" items="${complexities}">
    <option value="${complexity.getId()}">
        <c:out value="${complexity.getName()}"/>
    </option>
</c:forEach>
</select><br>
    Time: <input name="time" type="number" min="5" max="240" step="5" value="5"> (min = 5 minutes, max = 240 minutes)
    <br>
    Number of questions:<input name="numberQ" type="number" min="1" max="100" value="1"> (min = 1, max = 100)
    <br>
    <input type="submit" value="Next">
    <input type="hidden" id="test_id" name="testId"  value="${test.getId()}">
    <div id="result"></div>
</form>
</body>
</html>
