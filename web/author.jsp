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
            <ul class="left hide-on-med-and-down">
                <FORM><INPUT class="waves-effect waves-light btn" Type="button" VALUE="Back" onClick="history.go(-1);return true;"></FORM>
            </ul>
            <ul class="right hide-on-med-and-down">

                <c:if test="${not empty user}">
                    <form  class="waves-effect waves-light btn" id="lo_button" action="controller" enctype="multipart/form-data" method="get">
                        <input type="hidden" name="command" value="logout" >
                        <input type="submit" value="Logout">
                    </form>
                </c:if>
            </ul>
        </div>
    </nav>
</div>
<main>

    <form action="controller" method="get" accept-charset="UTF-8" enctype="multipart/form-data">
        <input type="hidden" name="author_id" value="${author.id}">
        <h3>${author.name} ${author.surname}</h3>
        <table class="striped">
            <thead>
            <tr>
                <th>Title</th>
                <th>Description</th>
            </tr>
            </thead>
            <c:forEach var="book" items="${books}">
                <c:if test="${book.idAuthor == author.id}">
                    <tr>
                        <td>${book.name}</td>
                        <td>${book.description}</td>
                        <c:if test="${not empty user}">
                            <td>
                                <form class="waves-effect waves-light btn" action="controller" enctype="multipart/form-data" method="get">
                                    <input type="hidden" name="command" value="view_book">
                                    <input type="hidden" name="bookid" value="${book.id}">
                                    <input type="submit" value="View">
                                </form>
                            </td>
                        </c:if>
                        <c:if test="${user.admin == true}">
                            <td>
                                <form class="waves-effect waves-light btn" action="controller" enctype="multipart/form-data" method="get">
                                    <input type="hidden" name="command" value="delete_book">
                                    <input type="hidden" name="bookid" value="${book.id}">
                                    <input type="submit" value="Delete">
                                </form>
                            </td>
                        </c:if>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </form>
</main>
<footer class="page-footer">
    <div class="container grey-text">
        © 2016 Copyright
        <span class="right">Made by LibraryServiceCompany</span>
    </div>
</footer>

</body>
</html>