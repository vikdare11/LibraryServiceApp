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
                <form action="controller" enctype="multipart/form-data" method="get" id="delete_book">
                  <input type="hidden" name="command" value="delete_book">
                  <input type="hidden" name="bookid" value="${book.id}">
                  <a href="javascript:{}"
                     onclick="if(confirm('Are you sure?')){document.getElementById('delete_book').submit();return true}else{return false;}"
                     class="waves-effect waves-light btn"
                  ><i class="fa fa-trash" aria-hidden="true"></i> </a>
                </form>

              </td>

              <td>
                <form action="controller" enctype="multipart/form-data" method="get" id="view_book">
                  <input type="hidden" name="command" value="view_book">
                  <input type="hidden" name="bookid" value="${book.id}">
                  <a href="javascript:{}"
                     onclick="document.getElementById('view_book').submit(); return false;"
                     class="waves-effect waves-light btn"
                  ><i class="fa fa-eye" aria-hidden="true"></i> </a>
                </form>

              </td>
            </c:when>
            <c:otherwise>
            <td>
              <form action="controller" enctype="multipart/form-data" method="get" id="view_book1">
                <input type="hidden" name="command" value="view_book">
                <input type="hidden" name="bookid" value="${book.id}">
                <a href="javascript:{}"
                   onclick="document.getElementById('view_book1').submit(); return false;"
                   class="waves-effect waves-light btn"
                ><i class="fa fa-eye" aria-hidden="true"></i> </a>
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
  <%@include file='footer.jsp'%>
</html>
