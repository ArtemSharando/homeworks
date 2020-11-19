<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="../../jspf/directive/lang.jspf" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body{
            background: black;
        }
        #problem{
            color: white;
            margin-left: 750px;
            margin-top: 300px;
        }

        #home_link{
            position: absolute;
            margin-left: 770px;
            margin-top: 200px;
        }
    </style>
</head>
<body>
<h1 id="problem"><c:out value="${ex}"/></h1>
<h3><a href="/home" id="home_link"><fmt:message key="completedTest_jsp.return"/></a></h3>
</body>
</html>

