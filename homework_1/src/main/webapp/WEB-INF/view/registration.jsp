<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="../jspf/directive/lang.jspf" %>

<html lang="${lang}">
<head>
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/style/style.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body{
            background-image: url(/resources/images/login.jpg)
        }

        table {
            font-family: FreeMono, monospace;
            font-size: 19px;
            text-align: center;
            color: white;
        }
        th {
            background: #BCEBDD;
            color: white;
            padding: 10px 20px;
        }
        th, td {
            border-width: 0 1px 1px 0;
            border-color: white;
        }
        th:first-child, td:first-child {
            text-align: center;
        }
        th:first-child {
            border-top-left-radius: 10px;
        }
        th:last-child {
            border-top-right-radius: 10px;
            border-right: none;
        }
        td {
            padding: 10px 20px;
        }
        td input{
            border-radius: 15px;
        }
        td input:focus{
            outline:none;
            border-radius: 15px;
        }
        tr:last-child td:first-child {
            border-radius: 0 0 0 10px;
        }
        tr:last-child td:last-child {
            border-radius: 0 0 10px 0;
        }
        tr td:last-child {
            border-right: none;
        }
        #button_reg_tab tr td:last-child{
            border-bottom: none;
        }
        #button_reg{
            background: white;
            border-radius: 100px;
            padding: .3em 1em;
            margin-left: 825px;
        }
        #button_reg:hover{
            color: white;
            background: #8638B2;
            border: 2px solid white;
        }

    </style>
</head>
<body>
<form id="registration_form" method="post">
<div class="firstContainer">
<table>
    <tr>
        <td>
            <fmt:message key="registration_jsp.name"/>
        </td>
    </tr>
    <tr>
        <td>
            <div class="name_text">
                <input id="name_text" type="text" name="name"/>
            </div>
        </td>
    </tr>
</table>
<table>
    <tr>

        <td>
            <fmt:message key="registration_jsp.surname"/>
        </td>
    </tr>
    <tr>
        <td>
            <div class="surname">
                <input type="text" name="surname"/>
            </div>
        </td>
    </tr>
</table>
<table>
    <tr>
        <td>
            <fmt:message key="login_jsp.label.username"/>
        </td>
    </tr>
    <tr>
        <td>
            <input type="text" name="username"/>
        </td>
    </tr>
</table>
<table>
    <tr>
        <td>
            <fmt:message key="login_jsp.label.password"/>
        </td>
    </tr>
    <tr>
        <td>
            <input type="password" name="password"/>
        </td>
    </tr>
</table>
</div>
    <br>
<table id="button_reg_tab">
    <tr>
        <td>
    <div class="buttonRegistration">
        <input type="submit" id="button_reg" value='<fmt:message key="registration_jsp.registration_button"/>'>
    </div>
        </td>
    </tr>
</table>
    </form>
</table>

</body>
</html>
