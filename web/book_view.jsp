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

<br>
    <br>
    <c:if test="${user.admin == true}">
        <form action="controller" enctype="multipart/form-data" method="get" id="edit_book">
            <input type="hidden" name="command" value="open_edit_book">
            <input type="hidden" name="book_id" value="${bookVO.book.id}">
            <a href="javascript:{}"
               onclick="document.getElementById('edit_book').submit(); return false;"
               class="waves-effect waves-light btn"
            >Edit book <i class="fa fa-edit" aria-hidden="true"></i></a>
        </form>

    </c:if>
    <h5>Author: ${bookVO.author.name} ${bookVO.author.surname}</h5>
    <h5>Title: ${bookVO.book.name}</h5>
    <h5>Description: ${bookVO.book.description}</h5>
    <h5>Count of views: ${bookVO.book.countOfViews}</h5>

    <form action="controller" enctype="multipart/form-data" method="get" id="add_to_collection">
        <input type="hidden" name="command" value="add_book_to_reader_collection">
        <input type="hidden" name="user_id" value="${user.id}">
        <input type="hidden" name="book_id" value="${bookVO.book.id}">
        <a href="javascript:{}"
            onclick="document.getElementById('add_to_collection').submit(); return false;"
            class="waves-effect waves-light btn"
        >Add to my collection </a>
    </form>

    <form action="controller" enctype="multipart/form-data" method="get" id="view_author">
        <input type="hidden" name="command" value="open_author">
        <input type="hidden" name="author_id" value="${bookVO.author.id}">
        <a href="javascript:{}"
           onclick="document.getElementById('view_author').submit(); return false;"
           class="waves-effect waves-light btn"
        >View author of book </a>
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
                    <form action="controller" enctype="multipart/form-data" method="get" id="delete_comment">
                        <input type="hidden" name="command" value="delete_comment">
                        <input type="hidden" name="comment_id" value="${comment.id}">
                        <input type="hidden" name="book_id" value="${bookVO.book.id}">
                        <a href="javascript:{}"
                           onclick="if(confirm('Are you sure?')){document.getElementById('delete_comment').submit();return true}else{return false;}"
                           class="waves-effect waves-light btn">
                            <i class="fa fa-trash" aria-hidden="true"></i> </a>
                    </form>


                </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <form action="controller" enctype="multipart/form-data" method="post" id="add_comment">
        <input type="hidden" name="command" value="add_comment">
        <input type="hidden" name="book_id" value="${bookVO.book.id}">
        <textarea maxlength="10000" name="review" placeholder="Input your review here..."></textarea>
        <a href="javascript:{}"
           onclick="document.getElementById('add_comment').submit(); return false;"
           class="waves-effect waves-light btn">
            Add comment </a>
    </form>



<br>
    <form action="controller" enctype="multipart/form-data" method="get" id="read_online">
        <input type="hidden" name="command" value="read_online">
        <input type="hidden" name="book_id" value="${bookVO.book.id}">
        <a href="javascript:{}"
           onclick="document.getElementById('read_online').submit(); return false;"
           class="waves-effect waves-light btn"
        >Read online </a>
    </form>

    <form action="controller" enctype="multipart/form-data" method="get" id="download_fb2">
        <input type="hidden" name="command" value="download_book">
        <input type="hidden" name="bookPath" value="${bookVO.downloadPaths[0].path}">
        <a href="javascript:{}"
           onclick="document.getElementById('download_fb2').submit(); return false;"
           class="waves-effect waves-light btn"
        >Download FB2 </a>
    </form>


    <form action="controller" enctype="multipart/form-data" method="get" id="download_pdf">
        <input type="hidden" name="command" value="download_book">
        <input type="hidden" name="bookPath" value="${bookVO.downloadPaths[1].path}">
        <a href="javascript:{}"
           onclick="document.getElementById('download_pdf').submit(); return false;"
           class="waves-effect waves-light btn"
        >Download PDF </a>
    </form>


    <form action="controller" enctype="multipart/form-data" method="get" id="download_txt">
        <input type="hidden" name="command" value="download_book">
        <input type="hidden" name="bookPath" value="${bookVO.downloadPaths[2].path}">
        <a href="javascript:{}"
           onclick="document.getElementById('download_txt').submit(); return false;"
           class="waves-effect waves-light btn"
        >Download TXT </a>
    </form>


</main>
</body>
    <%@include file='footer.jsp'%>
</html>