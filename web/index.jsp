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
      <ul class="left hide-on-med-and-down">
        <c:if test="${not empty user}">
          <h3>Hello, ${user.login}! Have a nice day.</h3>
          <form action="controller" enctype="multipart/form-data" method="get">
            <input type="hidden" name="command" value="logout">
            <input type="submit" value="Logout">
          </form>
          <form action="controller" enctype="multipart/form-data" method="get">
            <input type="hidden" name="command" value="open_user">
            <input type="hidden" name="user_id" value="${user.id}">
            <input type="submit" value="My profile">
          </form>
          <form action="controller" enctype="multipart/form-data" method="get">
            <input type="hidden" name="command" value="get_users">
            <input type="submit" value="Users list">
          </form>
          <form action="controller" enctype="multipart/form-data" method="get">
            <input type="hidden" name="command" value="generate_pdf_documents">
            <input type="submit" value="Generate pdf documents">
          </form>
          <form action="controller" enctype="multipart/form-data" method="get">
            <input type="hidden" name="command" value="generate_xls_documents">
            <input type="submit" value="Generate xls documents">
          </form>
          <form action="controller" enctype="multipart/form-data" method="get">
            <input type="hidden" name="command" value="generate_csv_documents">
            <input type="submit" value="Generate csv documents">
          </form>
        </c:if>
      </ul>
      <ul class="right hide-on-med-and-down">
        <c:if test="${empty user}">
          <a href="login.jsp">Sign in</a>
          <a href="registration.jsp">Sign up</a>
        </c:if>
      </ul>
    </div>
  </nav>
</div>

<main>
  <div id="hero" style="height: 490px;">
    <div class="container" id="hero-text-container">
      <div class="row">
        <div class="col s12 center-align">
          <h1 id="hero-title" itemprop="description" style="font-size: 35px;">

          <span class="thin">
             Cafe, transport, home...
            <br>
            Now all Your favorite books will always at hand.
            </span>
            <br>
            <span class="bold">LibraryService</span>
             <span class="thin">
               will allow You to read at any place and in any situation
               <br>
               Quick, easy, convenient.
               </span>
          </h1>
        </div>
      </div>
      <div class="row">
        <div class="col s12">
          <div class="center-align">
            <form class="waves-effect waves-light btn" action="controller" enctype="multipart/form-data" method="get">
              <input type="hidden" name="command" value="get_books">
              <input type="submit" value="Books">
            </form>
            <form class="waves-effect waves-light btn" action="controller" enctype="multipart/form-data" method="get">
              <input type="hidden" name="command" value="get_authors">
              <input type="submit" value="Authors">
            </form>
          </div>

        </div>
      </div>
    </div>
  </div>
</main>

<footer class="page-footer">

  <div class="container grey-text">
    © 2016 Copyright
    <span class="right">Made by LibraryServiceCompany</span>

  </div>
</footer>
</body>
</html>