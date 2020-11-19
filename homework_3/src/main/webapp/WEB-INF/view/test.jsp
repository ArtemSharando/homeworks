<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="../jspf/directive/lang.jspf" %>
<html lang="${lang}">
<head>
    <title>Test</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/style/style.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function (e) {
            var $worked = $("#worked");

            function update() {
                var myTime = $worked.html();
                var ss = myTime.split(":");
                var dt = new Date();
                dt.setHours(0);
                dt.setMinutes(ss[0]);
                dt.setSeconds(ss[1]);

                var dt2 = new Date(dt.valueOf() - 1000);
                var temp = dt2.toTimeString().split(" ");
                var ts = temp[0].split(":");

                $worked.html(ts[1]+":"+ts[2]);
                setTimeout(update, 1000);
            }

            setTimeout(update, 1000);
        });
    </script>
    <style>
        body{
            background-image: url(/resources/images/home.jpg);
            /*background: black;*/
            background-size: cover;
        }
        table{
            font-family: FreeMono, monospace;
            font-size: 19px;
            color: white;
            margin-left: 800px;
            margin-top: 2px;
        }
        #test_name{
            color: white;
            font-family: FreeMono, monospace;
            margin-left: 880px;
            margin-top: 50px;
            font-size: 35px;
        }
        #worked{
            color: white;
            font-family: FreeMono, monospace;
            margin-left: 910px;
            margin-top: 10px;
            font-size: 35px;
        }
        #tr_q{
            height: 100px;
        }
        #confirm{
            margin-left: 880px;
            margin-top: 50px;
            font-family: FreeMono, monospace;
            border-radius: 50px; /* Скругляем уголки */
            background: white;
            padding: .7em 2.5em;
            font-size: 17px;
        }
        #confirm:hover{
            color: white;
            background: #8638B2;
            border: 2px solid white;
        }
        #confirm:focus{
            outline: none;
            border-radius: 50px
        }
    </style>
</head>
<body>
<div id="test_name" ><c:out value="${name}"/></div><br>
<div id="worked"><c:out value="${time}"/>:00</div>
<form id="question_test" action="test" method="post">
<table>
    <c:forEach var="q" items="${list}">
        <tr id="tr_q">
            <th id="counter_q"><c:out value="${q.getCounterQuestion()}"/>.</th>
            <th><c:out value="${q.getContent()}"/></th>
        </tr>
        <c:forEach var="a" items="${q.getAnswerOfQ()}">
            <tr>
                <td>
                    <input type="checkbox"  name="correctAnswer${a.getId()}" value="${a.isCorrectAnswer()}"></td>
                <td><c:out value="${a.getVariant()}"/></td>
            </tr>
        </c:forEach>
    </c:forEach>

</table>
<input id="confirm" type="submit" value="Confirm" ><br>
</form>
<div>1</div><br>
</body>
</html>
