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

    <h1>Login: ${userVO.login}</h1>
    <h1>Email: ${userVO.email}</h1>
    <h1>Book collection:</h1>
    <table class="striped">
        <thead>
        <tr>
            <th>Author</th>
            <th>Title</th>
            <th>Description</th>
        </tr>
        </thead>
        <c:forEach var="book" items="${userVO.bookCollection}">
            <tr>
                <td>${book.author}</td>
                <td>${book.name}</td>
                <td>${book.description}</td>
                <td>
                    <form action="controller" enctype="multipart/form-data" method="get" id="view_book">
                        <input type="hidden" name="command" value="view_book">
                        <input type="hidden" name="bookid" value="${book.id}">
                        <a href="javascript:{}"
                           onclick="document.getElementById('view_book').submit(); return false;"
                           class="waves-effect waves-light btn"
                        ><i class="fa fa-eye" aria-hidden="true"></i></a>
                    </form>

                </td>
                <c:if test="${user.login == userVO.login}">
                <td>
                    <form action="controller" enctype="multipart/form-data" method="get" id="remove_book">
                        <input type="hidden" name="command" value="remove_book_from_reader_collection">
                        <input type="hidden" name="bookid" value="${book.id}">
                        <a href="javascript:{}"
                           onclick="document.getElementById('remove_book').submit(); return false;"
                           class="waves-effect waves-light btn"
                        ><i class="fa fa-minus" aria-hidden="true"></i></a>
                    </form>

                    <form class="waves-effect waves-light btn" action="controller" enctype="multipart/form-data" method="get">
                        <input type="hidden" name="command" value="remove_book_from_reader_collection">
                        <input type="hidden" name="bookid" value="${book.id}">
                        <input type="submit" value="Remove"/>
                    </form>
                </td>
                </c:if>
                <c:if test="${user.admin == true}">
                    <td>
                        <form action="controller" enctype="multipart/form-data" method="get" id="delete_book">
                            <input type="hidden" name="command" value="delete_book">
                            <input type="hidden" name="bookid" value="${book.id}">
                            <a href="javascript:{}"
                               onclick="if(confirm('Are you sure?')){document.getElementById('delete_book').submit();return true}else{return false;}"
                               class="waves-effect waves-light btn"
                            ><i class="fa fa-trash" aria-hidden="true"></i></a>
                        </form>

                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>

</main>
</body>
    <%@include file='footer.jsp'%>
</html>
