<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Library Service</title>
  <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link type="text/css" rel="stylesheet" href="assets/css/materialize.min.css"  media="screen,projection"/>
  <link  rel="stylesheet" href="assets/style.scss" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
</head>
<body>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script type="text/javascript" src="assets/js/materialize.min.js"></script>

<%@include file = 'header.jsp' %>

<main>
  <form action="controller" method="post" accept-charset="UTF-8" enctype="multipart/form-data">
  <input type="hidden"name="command" value="add_author">

  <div class="row">
    <div class="input-field col s6" id="log">

      <input type="text" id="name" maxlength="45"  name="author_name"  class="validate">
      <label class="active" for="name">Name</label>
    </div>
  </div>

  <div class="row">
    <div class="input-field col s6" id="pass">

      <input type="text" id="surname" maxlength="45"  name="author_surname"  class="validate">
      <label class="active" for="surname">Surname</label>
    </div>
  </div>

  <button class="btn waves-effect waves-light" id="log_button" input type="submit" name="action">Submit
    <i class="material-icons right">send</i>
  </button>

</form>
</main>
</body>
  <%@include file='footer.jsp'%>
</html>
