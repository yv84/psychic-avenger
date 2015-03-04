<%@ page language="java" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title><fmt:message key="welcome.title"/></title>
    <link rel="stylesheet" href="<c:url value="/resources/assets/css/main.css" />"
          type="text/css" media="screen">
    <title></title>
</head>
<body>
    index.jsp
    <div class="container">
        <h1>
            <fmt:message key="welcome.title"/>
        </h1>
        <p>
            Locale = ${pageContext.response.locale}
        </p>
        <hr>Language :
        <ul>
            <li> <a href="?locale=en_US">us</a> |  <a href="?locale=ru_RU">ru</a>  </li>
        </ul>
        <form action="/login" method="POST">
            <input type="text" name="username"/>
            <input type="submit"/>
        </form>
    </div>
    <script src="<c:url value="/resources/assets/js/main.js" />"></script>
</body>
</html>
