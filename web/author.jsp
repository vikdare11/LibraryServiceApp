<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:if test="${not empty user}">
    <form action="controller" enctype="multipart/form-data" method="post">
        <input type="hidden" name="command" value="logout">
        <input type="submit" value="Logout">
    </form>
</c:if>
<form action="controller" method="post" accept-charset="UTF-8" enctype="multipart/form-data">
  <input type="hidden" name="author_id" value="${author.id}">
    <h3>${author.name} ${author.surname}</h3>
    <table border="1">
        <tr>
            <th>Title</th>
            <th>Description</th>
        </tr>
        <c:forEach var="book" items="${books}">
            <c:if test="${book.idAuthor == author.id}">
                <tr>
                    <td>${book.name}</td>
                    <td>${book.description}</td>
                    <td>
                        <form action="controller" enctype="multipart/form-data" method="post">
                            <input type="hidden" name="command" value="view_book">
                            <input type="hidden" name="bookid" value="${book.id}">
                            <input type="submit" value="View"/>
                        </form>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</form>

</body>
</html>
