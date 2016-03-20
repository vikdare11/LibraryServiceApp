<%--
  Created by IntelliJ IDEA.
  User: Vika
  Date: 3/20/2016
  Time: 8:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

  <h3>Surname:</h3>
  <input type="text" name="author_surname" value="${author.surname}">

  <h3>Name:</h3>
  <input type="text" name="author_name" value="${author.name}">

  <input type="submit" value="Apply changes">

</form>

</body>
</html>
