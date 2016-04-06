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
        <form  class="waves-effect waves-light btn" id="logout" action="controller" enctype="multipart/form-data" method="get">
          <input type="hidden" name="command" value="logout" >
          <input type="submit" value="Logout">
        </form>
      </ul>
      <ul class="left hide-on-med-and-down">
        <form  class="waves-effect waves-light btn" id="back" >
          <input type="submit" value="Back" onClick="history.go(-1);return true;">
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
  <table class="striped">
    <thead>
    <tr>
      <th>Author</th>
      <th>Name</th>
      <th>Description</th>
    </tr>
    </thead>
    <c:forEach var="book" items="${books}">
      <tr>
        <td>${book.author}</td>
        <td>${book.name}</td>
        <td>${book.description}</td>

        <c:if test="${not empty user}">
          <c:choose>
            <c:when test="${user.admin}">
              <td>
                <form class="waves-effect waves-light btn" action="controller" enctype="multipart/form-data" method="get">
                  <input type="hidden" name="command" value="delete_book">
                  <input type="hidden" name="bookid" value="${book.id}">
                  <input type="submit" value="Delete">
                </form>
              </td>

              <td>
                <form class="waves-effect waves-light btn" action="controller" enctype="multipart/form-data" method="get">
                  <input type="hidden" name="command" value="view_book">
                  <input type="hidden" name="bookid" value="${book.id}">
                  <input type="submit" value="View"/>
                </form>
              </td>
            </c:when>
            <c:otherwise>
            <td>
              <form class="waves-effect waves-light btn" action="controller" enctype="multipart/form-data" method="get">
                <input type="hidden" name="command" value="view_book">
                <input type="hidden" name="bookid" value="${book.id}">
                <input type="submit" value="View"/>
              </form>
            </td>
            </c:otherwise>
          </c:choose>
        </c:if>
      </tr>
    </c:forEach>
  </table>





</main>
</body>
<footer class="page-footer" id="footer">
  <div class="container grey-text">
    © 2016 Copyright
    <span class="right">Made by LibraryServiceCompany</span>
  </div>
</footer>
</html>
