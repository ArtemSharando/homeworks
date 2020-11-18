<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="../jspf/directive/lang.jspf" %>

<html lang="${lang}">
<head>
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/style/style.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body{
            background-image: url(/resources/images/3d-grafika-abstrakciya--abstract-xiaomi--1314822.jpg)
        }
        .profile_name{
            color: white;
            font-family: FreeMono, monospace;
            font-size: 20px;
            margin-left: 100px;
            margin-top: 40px;
        }
        .result_test{
            color: white;
            font-family: FreeMono, monospace;
            font-size: 20px;
            margin-left: 650px;
            margin-top: 100px;
        }
        #button_home{
            position: absolute;
            margin-left: 800px;
            margin-top: 300px;
            font-family: FreeMono, monospace;
            border-radius: 50px; /* Скругляем уголки */
            padding: .7em 2.5em;
            font-size: 17px;
            text-decoration: none;
            color: white;
            background: #9644C5;
        }
        #button_home:hover{
            color: black;
            background: #8638B2;
            font-size: 17px;
        }
        #button_home:focus{
            outline: none;
            border-radius: 50px
        }
    </style>
</head>
<body>
<body>
<div class="profile_name">
<fmt:message key="profile_jsp.username"/>: <c:out value="${username}"/><br>
<fmt:message key="profile_jsp.name"/>: <c:out value="${name}"/> <br>
</div>
<div class="result_test">
<fmt:message key="profile_jsp.list"/>:<br>
<c:forEach var="l" items="${list}">
    <fmt:message key="profile_jsp.test_name"/>: <c:out value="${l.getTestName()}"/> ||
    <fmt:message key="profile_jsp.result"/>: <c:out value="${l.getResult()}"/>%<br>
</c:forEach>
</div>
<a id="button_home" href="home"><fmt:message key="completedTest_jsp.return"/></a>
</body>
</body>
</html>
