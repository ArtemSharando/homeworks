<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="../jspf/directive/lang.jspf" %>

<html lang="${lang}">
<head>
    <title>Menu</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/resources/style/style.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body{
            background-image: url(/resources/images/home.jpg)
        }
    </style>
</head>
<body>
<form id="buttonLang" action="home" method="post">
    <input type="submit" name="lang" value="ru" id="ru">
    <input type="submit" name="lang" value="en" id="en">
</form>

<c:if test="${isLogged != null}">
    <a id="profile_link" href="profile" title="<fmt:message key="home_jsp.list.profile"/>"><img src="../../resources/images/profile.png" width="40" height="40"></a>
</c:if>
<c:if test="${isLogged == null}">
    <a id="registration_link" href="registration"><fmt:message key="home_jsp.list.registration"/></a><br>
</c:if>
<c:if test="${isLogged != null}">
    <a id="logout_link" href="logout" title="<fmt:message key="home_jsp.list.logout"/>"><img src="../../resources/images/log2.png" width="40" height="40" ></a>
</c:if>
<c:if test="${isLogged != null}">
    <a id="take_test" href="takeTest"><fmt:message key="home_jsp.list.take_test"/></a>
</c:if>
<c:if test="${isAdmin != null}">
    <a id="test_link" href="testAdmin"><fmt:message key="home_jsp.list.test"/></a>
</c:if>
<c:if test="${isLogged == null}">
    <div class="just_registr"> <fmt:message key="home_jsp.list.already"/> <a id="login_link" href="login"><fmt:message key="home_jsp.list.login"/></a></div>
</c:if>
<c:if test="${isAdmin != null}">
    <a id="user_link" href="userAdmin"><fmt:message key="home_jsp.list.user"/></a>
</c:if>

</body>
</html>
