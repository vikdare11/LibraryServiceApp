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
      <th>Book id</th>
      <th>Name</th>
      <th>Author id</th>
      <th>Description</th>
      <th>Count of views</th>
    </tr>
    <c:forEach var="book" items="${books}">
      <tr>
        <td>${book.id}</td>
        <td>${book.name}</td>
        <td>${book.id}</td>
        <td>${book.description}</td>
        <td>${book.countOfViews}</td>
      </tr>
    </c:forEach>
  </table>
</body>
</html>
