<%@ page import="models.Result" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: cun
  Date: 09.09.2025
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Лаб 2 по Вебу</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/result.css">
</head>
<body>
<header>
    <div>
        <div class="hover-card">
            <div class="card-container">
                <i id="author-symbol" class="fa-solid fa-user-circle"> </i>
                <span id="author-name"> HOANG THE VIET </span>
                <div class="card-data">
                            <span class="card-description">
                                <strong>Hoang The Viet</strong> from <strong>P3232</strong> with variant <strong>415129</strong>
                            </span>
                </div>
            </div>
        </div>
    </div>

    <div class="social-icons">
        <a href="https://www.linkedin.com/in/vietht-hl/"><i class="fa-brands fa-linkedin"> </i></a>
        <a href="https://github.com/viet1m96"><i class="fa-brands fa-github"> </i></a>
        <a href="https://www.facebook.com/viet.thehoang.9"><i class="fa-brands fa-facebook"> </i></a>
    </div>
</header>
<div class="result-container">
    <table id="result_table">
        <tr>
            <td id="result" colspan="6">
                <h1>Result of validation</h1>
            </td>
        </tr>
        <tr>
            <th>X</th>
            <th>Y</th>
            <th>R</th>
            <th>Hit</th>
            <th>Calculation Time</th>
            <th>Release Time</th>
        </tr>
        <% List<Result> history = (List<Result>)session.getAttribute("history");
            Result lastElement = history.get(history.size() - 1);
        %>
        <tr>
            <td> <%= lastElement.getX() %>  </td>
            <td> <%= lastElement.getY() %> </td>
            <td> <%= lastElement.getR() %> </td>
            <td> <%= lastElement.isHit() %> </td>
            <td> <%= lastElement.getCalTime()%> </td>
            <td> <%=lastElement.getReleaseTime()%> </td>
        </tr>
    </table>
    <div id="goBack">
        <a href="${pageContext.request.contextPath}/index.jsp"> Back to form </a>
    </div>
</div>


</body>
</html>