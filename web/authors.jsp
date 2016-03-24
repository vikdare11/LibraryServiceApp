<%--
  Created by IntelliJ IDEA.
  User: Vika
  Date: 3/1/2016
  Time: 8:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
  <c:if test="${user.admin == true}">
    <a href="add_author.jsp">Add author</a>
  </c:if>

  <c:forEach var="author" items="${authors}">
    <h3>${author.name} ${author.surname}</h3>
    <form action="controller" enctype="multipart/form-data" method="post">
      <input type="hidden" name="command" value="open_author">
      <input type="hidden" name="author_id" value="${author.id}">
      <input type="submit" value="View books"/>
    </form>
    <c:if test="${user.admin == true}">
      <form action="controller" enctype="multipart/form-data" method="post">
        <input type="hidden" name="command" value="prepare_add_book">
        <input type="hidden" name="author_id" value="${author.id}">
        <input type="submit" value="Add book"/>
      </form>
      <form action="controller" enctype="multipart/form-data" method="post">
        <input type="hidden" name="command" value="open_edit_author">
        <input type="hidden" name="author_id" value="${author.id}">
        <input type="submit" value="Edit">
      </form>
      <form action="controller" enctype="multipart/form-data" method="post">
        <input type="hidden" name="command" value="delete_author">
        <input type="hidden" name="author_id" value="${author.id}">
        <input type="submit" value="Delete">
      </form>
    </c:if>
  </c:forEach>
</body>
</html>
