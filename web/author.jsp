<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="controller" method="post" accept-charset="UTF-8" enctype="multipart/form-data">
  <input type="hidden"name="command" value="edit_author">
  <input type="hidden" name="author_id" value="${author.id}">



  <input type="submit" value="apply changes">

</form>

</body>
</html>
