<%@ page language="java" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en" class="">
<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <META http-equiv="Content-Language" content="en">
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

    <div id="account_handler">
        <c:forEach items="${accounts}" var="account">
            <p>
             <span class="account_id">${account.id}</span>,
             <span class="account_username">${account.username}</span>,
             <a class="account_delete" href="/account/${account.id}">Delete</a>
            </p>
        </c:forEach>
        <p>
            <input class="account_add" type="text" value="" />
            <a class="account_add btn" href="#" >Add</a>
        </p>
    </div>
    <div id="container"
         style="min-width: 728px; height: 400px; margin: 0 auto"></div>
    <%-- jquery --%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <%--Latest compiled and minified JavaScript--%>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/assets/js/main.js" />"></script>
    <%-- highcharts --%>
    <script src="http://code.highcharts.com/highcharts.js"></script>
    <script src="http://code.highcharts.com/modules/exporting.js"></script>

<script>
    $(function () {
        parseDate = function (sqlDateStr) {
            sqlDateStr = sqlDateStr.replace(/:| /g,"-");
            var YMDhms = sqlDateStr.split("-");
            var sqlDate = new Date();
            sqlDate.setFullYear(
                    parseInt(YMDhms[0]),
                    parseInt(YMDhms[1])-1,
                    parseInt(YMDhms[2])
            );
            sqlDate.setHours(
                    parseInt(YMDhms[3]),
                    parseInt(YMDhms[4]),
                    parseInt(YMDhms[5]),
                    0/*msValue*/
            );
            return sqlDate;
        };
        $('#container').highcharts({
            chart: {
                type: 'spline'
            },
            title: {
                text: 'Revenue'
            },
            subtitle: {
                text: 'Revenue for each day of last month'
            },
            xAxis: {
                type: 'datetime',
                dateTimeLabelFormats: { // don't display the dummy year
                    month: '%e. %b',
                    year: '%b'
                },
                title: {
                    text: 'Date'
                }
            },
            yAxis: {
                title: {
                    text: 'Money'
                },
                min: 0
            },
            tooltip: {
                headerFormat: '<b>{series.name}</b><br>',
                pointFormat: '{point.x:%e. %b}: {point.y}'
            },

            plotOptions: {
                spline: {
                    marker: {
                        enabled: true
                    }
                },
                series: {
                    connectNulls: true
                }
            },

            series: [{
                name: 'Выручка',
                // Define the data points. All series have a dummy year
                // of 1970/71 in order to be compared on the same x axis. Note
                // that in JavaScript, months start at 0 for January, 1 for February etc.
                data: [
                    <c:forEach items="${revenues}" var="v_item">
                    [parseDate('${v_item['created']}').getTime(),
                        ${v_item['revenue']} ],
                    </c:forEach>
                ]
            }]
        });
    });
</script>


</body>
</html>
