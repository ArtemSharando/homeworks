<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="../jspf/directive/lang.jspf" %>
<html lang="${lang}">
<head>
    <title>Complete</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/style/style.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body{
            background: black;
        }
        .result{
            font-family: FreeMono, monospace;
            font-size: 25px;
            color: white;
            margin-left: 940px;

        }
        .phase{
            font-family: FreeMono, monospace;
            font-size: 25px;
            color: white;
            margin-left: 870px;
            margin-top: 150px;
        }
        #return_button{
            margin-left: 815px;
            margin-top: 400px;
            font-family: FreeMono, monospace;
            border-radius: 50px; /* Скругляем уголки */
            background: white;
            padding: .7em 2.5em;
            font-size: 17px;
        }
        #return_button:hover{
            color: white;
            background: #8638B2;
            border: 2px solid white;
        }
        #return_button:focus{
            outline: none;
            border-radius: 50px
        }
    </style>
</head>
<body>
<div class="phase">
<fmt:message key="completedTest_jsp.result"/>
</div>
<div class="result">
<c:out value="${result}%"/>
</div>
<form method="post">
<input id="return_button" type="submit" value="<fmt:message key="completedTest_jsp.return"/>">
</form>
</body>
</html>
