<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Library Service</title>
  <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link type="text/css" rel="stylesheet" href="assets/css/materialize.min.css"  media="screen,projection"/>
  <link  rel="stylesheet" href="assets/style.scss" />
</head>
<body>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script type="text/javascript" src="assets/js/materialize.min.js"></script>
<div class="navbar-fixed">
  <nav>
    <div class="nav-wrapper">
      <a href="index.jsp" class="brand-logo center">Library Service</a>
      <ul class="right hide-on-med-and-down">
        <c:if test="${not empty user}">
        <form class="waves-effect waves-light btn" id="profile" action="controller" enctype="multipart/form-data" method="get">
          <input type="hidden" name="command" value="open_user">
          <input type="hidden" name="user_id" value="${user.id}">
          <input type="submit" value="My profile">
        </form>
        <form  class="waves-effect waves-light btn" id="logout" action="controller" enctype="multipart/form-data" method="get">
          <input type="hidden" name="command" value="logout" >
          <input type="submit" value="Logout">
        </form>
      </ul>
      <ul class="left hide-on-med-and-down">
        <form  class="waves-effect waves-light btn" id="back" >
          <input type="submit" value="Back" onClick="history.go(-1);return true;">
        </form>

        <form class="waves-effect waves-light btn" id="all" action="controller" enctype="multipart/form-data" method="get">
          <input type="hidden" name="command" value="get_books">
          <input type="submit" value="All books">
        </form>
        </c:if>
        <c:if test="${empty user}">
          <a href="login.jsp">Sign in</a>
          <a href="registration.jsp">Sign up</a>
        </c:if>
      </ul>
    </div>
  </nav>
</div>

<main>
  <form action="controller" method="post" accept-charset="UTF-8" enctype="multipart/form-data">
  <input type="hidden"name="command" value="add_book">
  <input type="hidden" name="author_id" value="${author_id}">

    <div class="row">
      <div class="input-field col s6" id="log">

        <input type="text" id="title" maxlength="45" required name="title"  class="validate">
        <label class="active" for="title">Title</label>
      </div>
    </div>

    <h1>Description:</h1>
    <textarea maxlength="10000" name="description" placeholder="Input book description here..."></textarea>
    <br>

    <h1>Path for online reading</h1>
    <button class="btn waves-effect waves-light"><input  id="path_button" type="file" name="read-file" ></button>
    <h1>Path for download in fb2</h1>
    <button class="btn waves-effect waves-light"><input   type="file" name="read-file" ></button>
    <h1>Path for download in pdf</h1>
    <button class="btn waves-effect waves-light"><input  type="file" name="read-file" ></button>
    <h1>Path for download in txt</h1>
    <button class="btn waves-effect waves-light"><input   type="file" name="read-file"></button>
    <br>
    <br>
    <button class="btn waves-effect waves-light"id="log_button" input type="submit" name="action">Submit
      <i class="material-icons right">send</i>
    </button>

</form>
  </main>
</body>
<footer class="page-footer">
  <div class="container grey-text">
    © 2016 Copyright
    <span class="right">Made by LibraryServiceCompany</span>
  </div>
</footer>
</html>
