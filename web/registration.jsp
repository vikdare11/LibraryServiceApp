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
        <a href="login.jsp">Sign in</a>
      </ul>
    </div>
  </nav>
</div>
<main>
  <form method="post" action="registration.action">
  <input type="hidden" name="command" value="register">

  <div class="row">
    <div class="input-field col s6" id="log">

      <input type="text" id="login" maxlength="45" required name="login"  class="validate">
      <label class="active" for="login">Login</label>
    </div>
  </div>


  <div class="row">
    <div class="input-field col s6" id="pass">

      <input type="password" id="password" maxlength="45" required name="password"  class="validate">
      <label class="active" for="password">Password</label>
    </div>
  </div>

  <div class="row">
    <div class="input-field col s6" id="mail">

      <input type="text" id="email" maxlength="45" required name="email"  class="validate">
      <label class="active" for="email">Email</label>
    </div>
  </div>




  <button class="btn waves-effect waves-light" id="log_button" input type="submit" name="action">Submit
    <i class="material-icons right">send</i>
  </button>
</form>
<h1></h1>
<c:if test="${not empty param['message']}">
  <p>User with this login is already exist</p>
</c:if>
  </main>
</body>
  <%@include file='footer.jsp'%>
</html>
