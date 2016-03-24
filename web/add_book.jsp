<%--
  Created by IntelliJ IDEA.
  User: Vika
  Date: 3/23/2016
  Time: 11:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="controller" method="post" accept-charset="UTF-8" enctype="multipart/form-data">
  <input type="hidden"name="command" value="add_book">
  <input type="hidden" name="author_id" value="${author_id}">

  <h3>Title:</h3>
  <input maxlength="45" type="text" name="title" placeholder="Pride and Prejudice">

  <h3>Description:</h3>
  <textarea name="description" placeholder="Input book description here..."></textarea>

  <h3>Path for online reading:</h3>
  <input type="text" name="readPath" placeholder="Books/book.html">

  <h3>Path for online reading:</h3>
  <input type="text" name="downloadPath" placeholder="Books/book.fb2">

  <input type="submit" value="Add book">

</form>
</body>
</html>
