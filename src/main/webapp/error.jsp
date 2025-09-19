<%--
  Created by IntelliJ IDEA.
  User: cun
  Date: 10.09.2025
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1>${param.error_code}: ${param.error_name}</h1>
<p>${param.msg}</p>
<a href="${pageContext.request.contextPath}/index.jsp">Back to the form</a>
</body>
</html>