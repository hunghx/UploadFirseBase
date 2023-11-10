<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hung1
  Date: 11/10/2023
  Time: 3:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Danh sách ảnh upload</h1>
<c:forEach items="${images}" varStatus="loop">
    <div>
        <img width="100" height="100" style="object-fit: cover" src="${loop.current}" alt="${loop.count}">
    </div>
</c:forEach>
</body>
</html>
