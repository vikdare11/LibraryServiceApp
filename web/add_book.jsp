<%--
  Created by IntelliJ IDEA.
  User: Vika
  Date: 3/23/2016
  Time: 11:20 PM
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
<FORM><INPUT Type="button" VALUE="Back" onClick="history.go(-1);return true;"></FORM>

<a href="index.jsp">Main page</a>
  <form action="controller" enctype="multipart/form-data" method="get">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="Logout">
  </form>

<form action="controller" method="post" accept-charset="UTF-8" enctype="multipart/form-data">
  <input type="hidden"name="command" value="add_book">
  <input type="hidden" name="author_id" value="${author_id}">

  <h3>Title:</h3>
  <input maxlength="45" type="text" name="title" placeholder="Pride and Prejudice">

  <h3>Description:</h3>
  <textarea maxlength="10000" name="description" placeholder="Input book description here..."></textarea>

  <%--<h3>Path for online reading:</h3>
  <input maxlength="255" type="text" name="readPath" placeholder="Books/book.html">--%>

  <h3>Path for download:</h3>
  <input type="file" name="file">

  <input type="submit" value="Add book">

</form>
</body>
</html>
