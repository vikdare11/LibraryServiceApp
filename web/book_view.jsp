<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Library Service</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="assets/css/materialize.min.css"  media="screen,projection"/>
    <link  rel="stylesheet" href="assets/style.scss" />
</head>
<body>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script type="text/javascript" src="assets/js/materialize.min.js"></script>
<div class="navbar-fixed">
    <nav>
        <div class="nav-wrapper">
            <a href="index.jsp" class="brand-logo center">Library Service</a>
            <ul class="right hide-on-med-and-down">
                <c:if test="${not empty user}">
                    <form class="waves-effect waves-light btn" id="profile" action="controller" enctype="multipart/form-data" method="get">
                        <input type="hidden" name="command" value="open_user">
                        <input type="hidden" name="user_id" value="${user.id}">
                        <input type="submit" value="My profile">
                    </form>
                <form  class="waves-effect waves-light btn" id="logout" action="controller" enctype="multipart/form-data" method="get">
                    <input type="hidden" name="command" value="logout" >
                    <input type="submit" value="Logout">
                </form>
            </ul>
            <ul class="left hide-on-med-and-down">
                <form  class="waves-effect waves-light btn" id="back" >
                    <input type="submit" value="Back" onClick="history.go(-1);return true;">
                </form>

                <form class="waves-effect waves-light btn" id="all" action="controller" enctype="multipart/form-data" method="get">
                    <input type="hidden" name="command" value="get_books">
                    <input type="submit" value="All books">
                </form>
                </c:if>
                <c:if test="${empty user}">
                    <a href="login.jsp">Sign in</a>
                    <a href="registration.jsp">Sign up</a>
                </c:if>
            </ul>
        </div>
    </nav>
</div>
<main>

<br>
    <br>
    <c:if test="${user.admin == true}">
        <form class="waves-effect waves-light btn" action="controller" enctype="multipart/form-data" method="get">
            <input type="hidden" name="command" value="open_edit_book">
            <input type="hidden" name="book_id" value="${bookVO.book.id}">
            <input type="submit" value="Edit book">
        </form>
    </c:if>
    <h4>Author: ${bookVO.author.name} ${bookVO.author.surname}</h4>
    <h4>Title: ${bookVO.book.name}</h4>
    <h5>Description: ${bookVO.book.description}</h5>
    <h4>Count of views: ${bookVO.book.countOfViews}</h4>
    <form class="waves-effect waves-light btn" action="controller" method="get" enctype="multipart/form-data">
        <input type="hidden" name="command" value="add_book_to_reader_collection">
        <input type="hidden" name="user_id" value="${user.id}">
        <input type="hidden" name="book_id" value="${bookVO.book.id}">
        <input type="submit" value="Add to my collection">
    </form>
    <form class="waves-effect waves-light btn" action="controller" enctype="multipart/form-data" method="get">
        <input type="hidden" name="command" value="open_author">
        <input type="hidden" name="author_id" value="${bookVO.author.id}">
        <input type="submit" value="View author of book">
    </form>
    <table class="striped">
        <thead>
        <tr>
            <th>User</th>
            <th>Review</th>
        </tr>
        </thead>
        <c:forEach var="comment" items="${bookVO.listOfComments}">
            <tr>
                <td>${comment.user}</td>
                <td>${comment.review}</td>
                <c:if test="${user.admin == true}">
                <td>
                    <form class="waves-effect waves-light btn" action="controller" enctype="multipart/form-data" method="get">
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
    <input class="waves-effect waves-light btn" type="submit" value="Add comment">
    </form>


<br>
<form class="waves-effect waves-light btn" id="path_button" action="controller" method="get" enctype="multipart/form-data">
    <input type="hidden" name="command" value="read_online">
    <input type="hidden" name="book_id" value="${bookVO.book.id}">
    <input type="submit" value="Read online">
</form>
<form class="waves-effect waves-light btn" action="download" method="get" enctype="multipart/form-data">
    <input type="hidden" name="command" value="download_book">
    <input type="hidden" name="bookPath" value="${bookVO.downloadPaths[0].path}">
    <input type="submit" value="Download FB2">
</form>

<form class="waves-effect waves-light btn" action="download" method="get" enctype="multipart/form-data">
    <input type="hidden" name="command" value="download_book">
    <input type="hidden" name="bookPath" value="${bookVO.downloadPaths[1].path}">
    <input type="submit" value="Download PDF">
</form>

<form class="waves-effect waves-light btn" action="download" method="get" enctype="multipart/form-data">
    <input type="hidden" name="command" value="download_book">
    <input type="hidden" name="bookPath" value="${bookVO.downloadPaths[2].path}">
    <input type="submit" value="Download TXT">
</form>




</main>

</body>
<footer class="page-footer" id="footer">
    <div class="container grey-text">
        © 2016 Copyright
        <span class="right">Made by LibraryServiceCompany</span>
    </div>
</footer>
</html>