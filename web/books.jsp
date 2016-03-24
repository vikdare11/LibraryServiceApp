<%--
  Created by IntelliJ IDEA.
  User: Vika
  Date: 2/5/2016
  Time: 11:33 PM
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
  <table border="1">
    <tr>
      <th>Author</th>
      <th>Name</th>
      <th>Description</th>
    </tr>
    <c:forEach var="book" items="${books}">
      <tr>
        <td>${book.author}</td>
        <td>${book.name}</td>
        <td>${book.description}</td>

        <c:if test="${not empty user}">
          <c:choose>
          <c:when test="${user.admin == true}">
          <td>
            <form action="controller" enctype="multipart/form-data" method="post">
              <input type="hidden" name="command" value="delete_book">
              <input type="hidden" name="bookid" value="${book.id}">
              <input type="submit" value="Delete">
            </form>
          </td>
          </c:when>
          <c:otherwise>
          <td>
            <form action="controller" enctype="multipart/form-data" method="post">
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
</body>
</html>
