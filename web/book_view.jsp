<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Library Service</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="assets/css/materialize.min.css" media="screen,projection"/>
    <link rel="stylesheet" href="assets/style.scss"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
</head>
<body>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script type="text/javascript" src="assets/js/materialize.min.js"></script>
<%@include file='header.jsp' %>
<main>

    <br>
    <br>
    <c:if test="${user.admin == true}">
        <form action="open_edit_book.action" method="post" id="edit_book${bookVO.book.id}">
            <input type="hidden" name="book_id" value="${bookVO.book.id}">
            <a href="javascript:{}"
               onclick="document.getElementById('edit_book${bookVO.book.id}').submit(); return false;"
               class="waves-effect waves-light btn"
                    >Edit book <i class="fa fa-edit" aria-hidden="true"></i></a>
        </form>

    </c:if>
    <table>
        <tr>
            <td class="leftcol">Author:</td>
            <td class="rightcol">${bookVO.author.name} ${bookVO.author.surname}</td>
        </tr>
        <tr>
            <td class="leftcol">Title:</td>
            <td class="rightcol">${bookVO.book.name}</td>
        </tr>
        <tr>
            <td class="leftcol">Description:</td>
            <td class="rightcol">${bookVO.book.description}</td>
        </tr>
        <tr>
            <td class="leftcol">Count of views:</td>
            <td class="rightcol">${bookVO.book.countOfViews}</td>
        </tr>
    </table>

    <form class="waves-effect waves-light btn" method="post" action="add_book_to_collection.action">
        <input type="hidden" name="user_id" value="${user.id}">
        <input type="hidden" name="book_id" value="${bookVO.book.id}">
        <input type="submit" value="Add to my collection">
        <i class="fa fa-plus" aria-hidden="true"></i>
    </form>

    <form class="waves-effect waves-light btn" action="open_author.action" method="post">
        <input type="hidden" name="author_id" value="${bookVO.author.id}">
        <input type="submit" value="View author of book">
        <i class="fa fa-eye" aria-hidden="true"></i>
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
                        <form action="delete_comment.action" method="post" id="delete_comment${comment.id}">
                            <input type="hidden" name="comment_id" value="${comment.id}">
                            <input type="hidden" name="book_id" value="${bookVO.book.id}">
                            <a href="javascript:{}"
                               onclick="if(confirm('Are you sure?')){document.getElementById('delete_comment${comment.id}').submit();return true}else{return false;}"
                               class="waves-effect waves-light btn">
                                <i class="fa fa-trash" aria-hidden="true"></i> </a>
                        </form>


                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <form action="add_comment.action" method="post" id="add_comment${bookVO.book.id}">
        <input type="hidden" name="command" value="add_comment">
        <input type="hidden" name="book_id" value="${bookVO.book.id}">
        <textarea maxlength="10000" name="review" placeholder="Input your review here..."></textarea>
        <a href="javascript:{}"
           onclick="document.getElementById('add_comment${bookVO.book.id}').submit(); return false;"
           class="waves-effect waves-light btn">
            Add comment </a>
    </form>


    <br>
    <table>
        <tr>
            <td>
                <form action="read_online.action" enctype="multipart/form-data" method="get" id="read_online">
                    <input type="hidden" name="book_id" value="${bookVO.book.id}">
                    <a href="javascript:{}"
                       onclick="document.getElementById('read_online').submit(); return false;"
                       class="waves-effect waves-light btn"
                            >Read online </a>
                </form>
            </td>
            <td>
                <form action="download_book.action" method="get" id="download_fb2">
                    <input type="hidden" name="bookPath" value="${bookVO.downloadPaths[0].path}">
                    <a href="javascript:{}"
                       onclick="document.getElementById('download_fb2').submit(); return false;"
                       class="waves-effect waves-light btn"
                            >Download FB2 </a>
                </form>
            </td>
            <td>
                <form action="download_book.action" method="get" id="download_pdf">
                    <input type="hidden" name="bookPath" value="${bookVO.downloadPaths[1].path}">
                    <a href="javascript:{}"
                       onclick="document.getElementById('download_pdf').submit(); return false;"
                       class="waves-effect waves-light btn"
                            >Download PDF </a>
                </form>
            </td>
            <td>
                <form action="download_book.action" method="get" id="download_txt">
                    <input type="hidden" name="bookPath" value="${bookVO.downloadPaths[2].path}">
                    <a href="javascript:{}"
                       onclick="document.getElementById('download_txt').submit(); return false;"
                       class="waves-effect waves-light btn"
                            >Download TXT </a>
                </form>
            </td>
        </tr>
    </table>

</main>
</body>
<%@include file='footer.jsp' %>
</html>