<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<br/>
<h2>Form upload file</h2>
<form action="<%=request.getContextPath()%>/upload/firebase" method="post" enctype="multipart/form-data">
  <input type="file" name="image" multiple>
  <button type="submit">Upload</button>
</form>
</body>
</html>