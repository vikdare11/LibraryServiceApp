<%--
  Created by IntelliJ IDEA.
  User: Vika
  Date: 3/23/2016
  Time: 11:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="controller" method="post" accept-charset="UTF-8" enctype="multipart/form-data">
  <input type="hidden"name="command" value="add_author">

  <h3>Name:</h3>
  <input type="text" name="author_name" placeholder="Joanne">

  <h3>Surname:</h3>
  <input type="text" name="author_surname" placeholder="Rowling">

  <input type="submit" value="Add author">

</form>
</body>
</html>
