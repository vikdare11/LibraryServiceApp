<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
    <form action="controller" enctype="multipart/form-data" method="post">
        <input type="hidden" name="command" value="logout">
        <input type="submit" value="Logout">
    </form>

    <form action="controller" enctype="multipart/form-data" method="post">
        <input type="hidden" name="command" value="get_users">
        <input type="submit" value="Go back">
    </form>

<form action="controller" method="post" accept-charset="UTF-8" enctype="multipart/form-data">
    <h1>Login: ${userVO.login}</h1>
    <h1>Email: ${userVO.email}</h1>
    <h1>Book collection of reader ${userVO.login}:</h1>
    <table border="1">
        <tr>
            <th>Author</th>
            <th>Title</th>
            <th>Description</th>
        </tr>
        <c:forEach var="book" items="${userVO.bookCollection}">
            <tr>
                <td>${book.author}</td>
                <td>${book.name}</td>
                <td>${book.description}</td>
                <td>
                    <form action="controller" enctype="multipart/form-data" method="post">
                        <input type="hidden" name="command" value="view_book">
                        <input type="hidden" name="bookid" value="${book.id}">
                        <input type="submit" value="View"/>
                    </form>
                </td>
                <c:if test="${user.login == userVO.login}">
                <td>
                    <form action="controller" enctype="multipart/form-data" method="post">
                        <input type="hidden" name="command" value="remove_book_from_reader_collection">
                        <input type="hidden" name="bookid" value="${book.id}">
                        <input type="submit" value="Remove from my collection"/>
                    </form>
                </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
