<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="../jspf/directive/lang.jspf" %>
<html lang="${lang}">
<head>
    <title>Test</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/style/style.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="../../resources/script.js"></script>
    <style>
        body{
            background-image: url(/resources/images/3d-grafika-abstrakciya--abstract-xiaomi--1314822.jpg)
        }
        table {
            font-family: FreeMono, monospace;
            font-size: 19px;
            text-align: center;
            color: white;
            margin-left: 500px;
            margin-top: 60px;
        }
        th {
            color: white;
            padding: 10px 20px;
            width: 15%;
        }
        th, td {
            border-width: 0 1px 1px 0;
            border-color: white;
        }
        td{
            color: white;
            height: 70px;
        }

        #choose_test{
            border-radius: 50px; /* Скругляем уголки */
            background: white;
            padding: .3em 1em;
            margin-right: 380px;
        }
        #choose_test:hover{
            color: white;
            background: #8638B2;
            border: 2px solid white;
        }
        #shapka{
            border-bottom: 1px solid white; /* Параметры рамки */
        }
        .form-control{
            font-family: FreeMono, monospace;
            border-radius: 50px; /* Скругляем уголки */
            margin-top: 280px;
            margin-left: 610px;
            width: 700px;
        }
        #choose_test:focus{
            outline: none;
            border-radius: 50px
        }
    </style>
</head>
<body>
<input class="form-control" type="text" placeholder="Поиск теста" id="search-text" onkeyup="tableSearch()">

    <table  id="test-table">
        <tr id="shapka">
            <th>Test Name</th>
            <th>Subject</th>
            <th>Complexity</th>
            <th>Number of questions</th>
        </tr>
        <tbody>
        <c:forEach var="tests" items="${list}">
        <tr>
            <td><c:out value="${tests.getName()}"/></td>
            <td><c:out value="${tests.getSubject()}"/></td>
            <td><c:out value="${tests.getComplexity()}"/></td>
            <td><c:out value="${tests.getNumberOfQuestion()}"/></td>

            <form method="post">
                <td >
                    <input type="hidden" name="test_id" value="${tests.getId()}">
                    <input type="submit" id="choose_test"  value="Choose Test">
                </td>
            </form>
        </tr>
        </c:forEach>
        </tbody>
        </table>
</body>
</html>
