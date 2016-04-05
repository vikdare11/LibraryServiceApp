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
        <FORM><INPUT class="waves-effect waves-light btn" Type="button" VALUE="Back" onClick="history.go(-1);return true;"></FORM>
      </ul>
      <ul class="right hide-on-med-and-down">

        <c:if test="${not empty user}">
          <form  class="waves-effect waves-light btn" id="lo_button" action="controller" enctype="multipart/form-data" method="get">
            <input type="hidden" name="command" value="logout" >
            <input type="submit" value="Logout">
          </form>
        </c:if>
      </ul>
    </div>
  </nav>
</div>
<main>

  <form action="controller" method="post" accept-charset="UTF-8" enctype="multipart/form-data">
    <input type="hidden"name="command" value="edit_book">
    <input type="hidden" name="book_id" value="${book.id}">

    <h1>Title:</h1>
    <input maxlength="45" type="text" name="title" placeholder="Pride and Prejudice">

    <h1>Description:</h1>
    <textarea maxlength="10000" name="description" placeholder="Input book description here..."></textarea>

    <h1>Path for online reading:</h1>
    <button class="btn waves-effect waves-light" input type="file" name="read-file">Choose file

    </button>

    <h1>Path for download in fb2:</h1>
    <button class="btn waves-effect waves-light" input type="file" name="fb2-file">Choose file

    </button>

    <h1>Path for download in pdf:</h1>
    <button class="btn waves-effect waves-light" input type="file" name="pdf-file">Choose file

    </button>

    <h1>Path for download in txt:</h1>
    <button class="btn waves-effect waves-light" input type="file" name="txt-file">Choose file
    </button>
    <br>
    <br>
    <button class="btn waves-effect waves-light" input type="submit" name="action">Submit
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