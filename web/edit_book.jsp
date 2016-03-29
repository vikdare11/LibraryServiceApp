<%--
  Created by IntelliJ IDEA.
  User: Vika
  Date: 3/29/2016
  Time: 10:29 PM
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
  <input type="hidden"name="command" value="edit_book">
  <input type="hidden" name="book_id" value="${book.id}">

  <h3>Title:</h3>
  <input maxlength="45" type="text" name="title" value="${book.name}">

  <h3>Description:</h3>
  <textarea maxlength="10000" name="description" value="${author.description}"></textarea>

  <h3>Path for online reading:</h3>
  <input type="file" name="read_file">

  <h3>Path for download in fb2:</h3>
  <input type="file" name="fb2_file">

  <h3>Path for download in pdf:</h3>
  <input type="file" name="pdf_file">

  <h3>Path for download in txt:</h3>
  <input type="file" name="txt_file">

  <input type="submit" value="Apply changes">

</form>


</body>
</html>
