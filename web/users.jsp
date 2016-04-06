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
<c:forEach var="person" items="${users}">
    <h3>${person.login}</h3>
    <form action="controller" enctype="multipart/form-data" method="get">
        <input type="hidden" name="command" value="open_user">
        <input type="hidden" name="user_id" value="${person.id}">
        <input type="submit" value="View information"/>
    </form>
    <c:if test="${user.admin == true}">
        <c:if test="${person.id != user.id && person.admin == false}">

            <form action="controller" enctype="multipart/form-data" method="get">
                <input type="hidden" name="command" value="edit_user">
                <input type="hidden" name="user_id" value="${person.id}">
                <input type="hidden" name="isadmin" value=true>
                <input type="submit" value="Make this user an admin"/>
            </form>
        </c:if>
        <c:if test="${person.admin == true && person.login != 'admin'}">
            <form action="controller" enctype="multipart/form-data" method="get">
                <input type="hidden" name="command" value="edit_user">
                <input type="hidden" name="user_id" value="${person.id}">
                <input type="hidden" name="isadmin" value=false>
                <input type="submit" value="Make this user not admin"/>
            </form>
        </c:if>
        <c:if test="${person.login != 'admin'}">
            <form action="controller" enctype="multipart/form-data" method="get">
                <input type="hidden" name="command" value="delete_user">
                <input type="hidden" name="user_id" value="${person.id}">
                <input type="submit" value="Delete">
            </form>
        </c:if>
    </c:if>
</c:forEach>
    </main>

</body>
<footer class="page-footer" id="footer">
    <div class="container grey-text">
        © 2016 Copyright
        <span class="right">Made by LibraryServiceCompany</span>
    </div>
</footer>
</html>