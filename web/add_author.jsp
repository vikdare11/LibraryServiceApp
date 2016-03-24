<%--
  Created by IntelliJ IDEA.
  User: Vika
  Date: 3/23/2016
  Time: 11:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:if test="${not empty user}">
  <form action="controller" enctype="multipart/form-data" method="post">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="Logout">
  </form>
</c:if>
<form action="controller" method="post" accept-charset="UTF-8" enctype="multipart/form-data">
  <input type="hidden"name="command" value="add_author">

  <h3>Name:</h3>
  <input maxlength="45" type="text" name="author_name" placeholder="Joanne">

  <h3>Surname:</h3>
  <input maxlength="45" type="text" name="author_surname" placeholder="Rowling">

  <input type="submit" value="Add author">

</form>
</body>
</html>
