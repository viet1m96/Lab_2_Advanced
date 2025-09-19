<%@ page import="models.Result" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: cun
  Date: 09.09.2025
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <meta charset="UTF-8">
    <title>Лаб 2 по Вебу</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/main.css">
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

    <div id="result-overlay" aria-live="polite">
        <span id="result-overlay-text"></span>
    </div>
    <div class="social-icons">
        <a href="https://www.linkedin.com/in/vietht-hl/"><i class="fa-brands fa-linkedin"> </i></a>
        <a href="https://github.com/viet1m96"><i class="fa-brands fa-github"> </i></a>
        <a href="https://www.facebook.com/viet.thehoang.9"><i class="fa-brands fa-facebook"> </i></a>
    </div>
</header>


<div class="working-container">
    <div class="left-panel">
        <div class="top-panel">
            <div class="label-area">
                Enter the parameters:
            </div>
            <form id="input-form" method="get" action="${pageContext.request.contextPath}/app">
                <div class="input-zone">
                    <label for="coordinateX" class="labels"> Coordinate X: </label>
                    <div class="input-and-condition">
                        <input id="coordinateX" name="x" type="text" required>
                        <div class="params_conditions"> (X must be in range [-5, 5]) </div>
                        <div id="xFeedback" class="input-feedback"></div>
                    </div>
                </div>

                <div class="input-zone">
                    <label for="ySelect" class="labels"> Coordinate Y: </label>
                    <div class="range-block" id="yOptions">
                        <select id="ySelect" name="y" required>
                            <option value="-2">-2</option>
                            <option value="-1.5">-1.5</option>
                            <option value="-1">-1</option>
                            <option value="-0.5">-0.5</option>
                            <option value="0">0</option>
                            <option value="0.5">0.5</option>
                            <option value="1">1</option>
                            <option value="1.5">1.5</option>
                            <option value="2">2</option>
                        </select>
                    </div>
                </div>

                <div class="input-zone">
                    <label class="labels"> Parameter R: </label>
                    <div class="R-block" id="rBlock">
                        <label class="option"><input type="checkbox" name="R" value="1" /><span>1</span></label>
                        <label class="option"><input type="checkbox" name="R" value="1.5" /><span>1.5</span></label>
                        <label class="option"><input type="checkbox" name="R" value="2" /><span>2</span></label>
                        <label class="option"><input type="checkbox" name="R" value="2.5" /><span>2.5</span></label>
                        <label class="option"><input type="checkbox" name="R" value="3" /><span>3</span></label>
                    </div>
                </div>

                <div class="submit-block" id="submitStatus">
                    <button id="submit-button"> Submit </button>
                </div>
            </form>
        </div>

        <div class="bottom-panel">
            <div class="label-area">
                Results
            </div>

            <table id="final-results" class="inner-table">
                <tr>
                    <th> Hit </th>
                    <th> X </th>
                    <th> Y </th>
                    <th> R </th>
                    <th> Calculation time </th>
                    <th> Release time </th>
                </tr>
            </table>

        </div>
    </div>

    <div class="right-panel">
        <div class="label-area">
            Illustration Graph
        </div>
        <canvas id="graph"></canvas>
        <div class="big-log">
            <div class="coordinate-log">
                <label>Current X: </label>
                <p id="screen-log-x"></p>
            </div>
            <div class="coordinate-log">
                <label>Current Y: </label>
                <p id="screen-log-y"></p>
            </div>
        </div>
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/DrawingGraph.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/Validate.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/DrawingPoints.js"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/Main.js"></script>

</body>
</html>