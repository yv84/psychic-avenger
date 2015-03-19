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
    <%--Latest compiled and minified CSS--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

    <%--Optional theme--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    
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

    <div>
        <c:forEach items="${accounts}" var="account">
            <p>
             ${account.id},
             ${account.username},
             <a href="/account/delete/${account.id}">Delete</a>
            </p>
        </c:forEach>
    </div>
    <%-- jquery --%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <%--Latest compiled and minified JavaScript--%>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/assets/js/main.js" />"></script>
</body>
</html>
