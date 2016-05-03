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
<br>
  <br>
  <c:if test="${user.admin == true}">
    <form action="add_author.jsp" enctype="multipart/form-data"  id="add_author">

      <a href="javascript:{}"
         onclick="document.getElementById('add_author').submit(); return false;"
         class="waves-effect waves-light btn"
      >Add author <i class="fa fa-plus" aria-hidden="true"></i></a>
    </form>

  </c:if>
  <table class="striped">
    <thead>
    <tr>
      <th>Author</th>
    </tr>
    </thead>
  <c:forEach var="author" items="${authors}">
    <tr>
    <td>
    <h5>${author.name} ${author.surname}</h5>
    </td>
    <td>
      <form action="get_book_by_author.action" method="post" id="${author.id}">
        <input type="hidden" name="author_id" value="${author.id}">
        <a href="javascript:{}"
           onclick="document.getElementById('${author.id}').submit(); return false;"
           class="waves-effect waves-light btn"
        ><i class="fa fa-eye" aria-hidden="true"></i></a>
      </form>

    </td>

    <c:if test="${user.admin == true}">
    <td>
      <form action="controller" enctype="multipart/form-data" method="get" id="add_book">
        <input type="hidden" name="command" value="prepare_add_book">
        <input type="hidden" name="author_id" value="${author.id}">
        <a href="javascript:{}"
           onclick="document.getElementById('add_book').submit(); return false;"
           class="waves-effect waves-light btn"
        ><i class="fa fa-plus" aria-hidden="true"></i></a>
      </form>

      </td>
    <td>
      <form action="controller" enctype="multipart/form-data" method="get" id="edit_author">
        <input type="hidden" name="command" value="open_edit_author">
        <input type="hidden" name="author_id" value="${author.id}">
        <a href="javascript:{}"
           onclick="document.getElementById('edit_author').submit(); return false;"
           class="waves-effect waves-light btn"
        ><i class="fa fa-edit" aria-hidden="true"></i></a>
      </form>

      </td>
    <td>
      <form action="controller" enctype="multipart/form-data" method="get" id="delete_author">
        <input type="hidden" name="command" value="delete_author">
        <input type="hidden" name="author_id" value="${author.id}">
        <a href="javascript:{}"
           onclick="if(confirm('Are you sure?')){document.getElementById('delete_author').submit();return true}else{return false;}"
           class="waves-effect waves-light btn"
        ><i class="fa fa-trash" aria-hidden="true"></i></a>
      </form>

      </td>
    </c:if>
  </c:forEach>
    </tr>
    </table>
  </main>
</body>
  <%@include file='footer.jsp'%>
</html>
