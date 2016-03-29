<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<FORM><INPUT Type="button" VALUE="Back" onClick="history.go(-1);return true;"></FORM>

<a href="index.jsp">Main page</a>

    <form action="controller" enctype="multipart/form-data" method="get">
        <input type="hidden" name="command" value="logout">
        <input type="submit" value="Logout">
    </form>
<form action="controller" enctype="multipart/form-data" method="get">
    <input type="hidden" name="command" value="get_books">
    <input type="submit" value="All books">
</form>
<form action="controller" enctype="multipart/form-data" method="get">
    <input type="hidden" name="command" value="open_user">
    <input type="hidden" name="user_id" value="${user.id}">
    <input type="submit" value="My profile">
</form>
<form action="controller" enctype="multipart/form-data" method="get">
    <input type="hidden" name="command" value="open_author">
    <input type="hidden" name="author_id" value="${bookVO.author.id}">
    <input type="submit" value="View author of book">
</form>
    <h1>Author: ${bookVO.author.name} ${bookVO.author.surname}</h1>
    <h1>Title: ${bookVO.book.name}</h1>
    <h1>Description: ${bookVO.book.description}</h1>
    <h1>Count of views: ${bookVO.book.countOfViews}</h1>
    <table border="1">
        <tr>
            <th>User</th>
            <th>Review</th>
        </tr>
        <c:forEach var="comment" items="${bookVO.listOfComments}">
            <tr>
                <td>${comment.user}</td>
                <td>${comment.review}</td>
                <c:if test="${user.admin == true}">
                <td>
                    <form action="controller" enctype="multipart/form-data" method="get">
                        <input type="hidden" name="command" value="delete_comment">
                        <input type="hidden" name="comment_id" value="${comment.id}">
                        <input type="hidden" name="book_id" value="${bookVO.book.id}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <form action="controller" method="post" enctype="multipart/form-data">
        <input type="hidden" name="command" value="add_comment">
        <input type="hidden" name="book_id" value="${bookVO.book.id}">
        <textarea maxlength="10000" name="review" placeholder="Input your review here..."></textarea>
        <input type="submit" value="Add comment">
    </form>
    <form action="controller" method="get" enctype="multipart/form-data">
        <input type="hidden" name="command" value="add_book_to_reader_collection">
        <input type="hidden" name="user_id" value="${user.id}">
        <input type="hidden" name="book_id" value="${bookVO.book.id}">
        <input type="submit" value="Add to my collection">
    </form>
    <form action="controller" method="get" enctype="multipart/form-data">
        <input type="hidden" name="command" value="read_online">
        <input type="hidden" name="book_id" value="${bookVO.book.id}">
        <input type="submit" value="Read online">
    </form>

    <form action="download" method="get" enctype="multipart/form-data">
        <input type="hidden" name="command" value="download_book">
        <input type="hidden" name="bookPath" value="${bookVO.downloadPaths[0].path}">
        <input type="submit" value="Download FB2">
    </form>

    <form action="download" method="get" enctype="multipart/form-data">
        <input type="hidden" name="command" value="download_book">
        <input type="hidden" name="bookPath" value="${bookVO.downloadPaths[1].path}">
        <input type="submit" value="Download PDF">
    </form>

    <form action="download" method="get" enctype="multipart/form-data">
        <input type="hidden" name="command" value="download_book">
        <input type="hidden" name="bookPath" value="${bookVO.downloadPaths[2].path}">
        <input type="submit" value="Download TXT">
    </form>
</body>
</html>