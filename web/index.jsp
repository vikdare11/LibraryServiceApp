<%--
  Created by IntelliJ IDEA.
  User: Vika
  Date: 2/5/2016
  Time: 10:47 PM
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
<c:if test="${not empty user}">
  <c:choose>
    <form action="controller" enctype="multipart/form-data" method="post">
      <input type="hidden" name="command" value="get_authors">
      <input type="submit" value="Authors list">
    </form>
    <form action="controller" enctype="multipart/form-data" method="post">
      <input type="hidden" name="command" value="get_books">
      <input type="submit" value="Books list">
    </form>
    <form action="controller" enctype="multipart/form-data" method="post">
      <input type="hidden" name="command" value="get_users">
      <input type="submit" value="Users list">
    </form>
</c:choose>
</c:if>
    <a href="login.jsp">Sign in</a>
    <a href="registration.jsp">Sign up</a>
  </body>
</html>
