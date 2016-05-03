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
    <form method="post" action="add_book.action">
    <input type="hidden" name="author_id" value="${author_id}">

    <div class="row">
      <div class="input-field col s6" id="log">

        <input type="text" id="title" maxlength="45" required name="title"  class="validate">
        <label class="active" for="title">Title</label>
      </div>
    </div>


    <div class="row">
      <div class="input-field col s6" id="desc">
          <textarea id="textarea1" name="description" class="materialize-textarea"></textarea>
          <label for="textarea1">Description</label>
      </div>
    </div>

    <br>

    <table>
      <tr>
        <td><h5>Path for online reading</h5></td>
        <td><h5>Path for download in fb2</h5></td>
        <td><h5>Path for download in pdf</h5></td>
        <td><h5>Path for download in txt</h5></td>
      </tr>
    <tr>
      <td>
    <input  id="path_online" type="file" name="read_file" >
      </td>
      <td>
   <input id="path_fb2"  type="file" name="fb2_file" >
      </td>
      <td>
<input id="path_pdf" type="file" name="pdf_file" >
      </td>
      <td>
    <input id="path_txt" type="file" name="txt_file">
      </td>
    </tr>
    </table>
    <br>
    <br>
    <button class="btn waves-effect waves-light"id="log_button" input type="submit" name="action">Submit
      <i class="material-icons right">send</i>
    </button>

</form>
  </main>
</body>
  <%@include file='footer.jsp'%>
</html>
