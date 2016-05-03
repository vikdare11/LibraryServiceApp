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

<script>
    window.onload = function onLoadFunc() {
        var type = "pdf_type";
        document.getElementById("inputTypeId").value = type;
        document.getElementById("inputTypeId1").value = type;
        document.getElementById("inputTypeId2").value = type;
        document.getElementById("inputTypeId3").value = type;
        document.getElementById("inputTypeId4").value = type;
    }

</script>

<script>
    function onChangeSelect() {
        var e = document.getElementById("selectId");
        var type = e.options[e.selectedIndex].value;
        document.getElementById("inputTypeId").value = type;
        document.getElementById("inputTypeId1").value = type;
        document.getElementById("inputTypeId2").value = type;
        document.getElementById("inputTypeId3").value = type;
        document.getElementById("inputTypeId4").value = type;
    }
</script>

<main>

    <div id="hero" style="height: 527px;">
        <div class="container" id="hero-text-container">
            <div class="row">
                <div class="col s12 center-align">
                    <h1 id="hero-title" itemprop="description" style="font-size: 35px;">
                        <c:if test="${not empty user}">
          <span class="thin">
             Hello,</span>
                        <span class="bold"> ${user.login}</span> <span class="thin">!
            <br>
            Now all Your favourite books will be always with You.

            <br>
            Please, choose book or author to find interesting book.
               <br>
               Or see what others are reading.
               </span>
                    </h1>
                </div>
            </div>
            </c:if>
            <c:if test="${empty user}">
          <span class="thin">
             Cafe, transport, home...
            <br>
            Now all Your favourite books will be with You.
            </span>
            <br>
            <span class="bold">LibraryService</span>
             <span class="thin">
               will allow You to read at any place and in any situation
               <br>
               Quick, easy, convenient.
               </span>
            </h1>
        </div>
    </div>
    </c:if>
    <div class="row">
        <div class="col s12">
            <div class="center-align">
                <table>
                    <tr>
                        <td style="padding-left: 100px">
                            <form method="post" action="get_books.action" id="the_form">
                                <a href="javascript:{}"
                                   onclick="document.getElementById('the_form').submit(); return false;"
                                   class="waves-effect waves-light btn"
                                        >Books <i class="fa fa-book" aria-hidden="true"></i></a>
                            </form>
                        </td>
                        <td>
                            <form action="controller" enctype="multipart/form-data" method="get" id="authors">
                                <input type="hidden" name="command" value="get_authors">
                                <a href="javascript:{}"
                                   onclick="document.getElementById('authors').submit(); return false;"
                                   class="waves-effect waves-light btn"
                                        >Authors <i class="fa fa-users" aria-hidden="true"></i></a>
                            </form>
                        </td>
                        <td>
                            <c:if test="${not empty user}">
                                <form method="post" action="get_users.action" id="get_users">
                                <input type="hidden" name="action">
                                    <a href="javascript:{}"
                                        onclick="document.getElementById('get_users').submit(); return false;"
                                        class="waves-effect waves-light btn"
                                            >Users <i class="fa fa-user" aria-hidden="true"></i></a>
                                </form>
                        </td>
                        <td>
                            <form action="view_user.action" method="post" id="user${user.id}">
                                <input type="hidden" name="user_id" value="${user.id}">
                                <a href="javascript:{}"
                                   onclick="document.getElementById('user${user.id}').submit(); return false;"
                                   class="waves-effect waves-light btn"
                                        >My profile <i class="fa fa-user-secret" aria-hidden="true"></i></a>
                            </form>
                            </c:if>
                        </td>
                    </tr>
                </table>

                <c:if test="${not empty user}">
                <table>
                    <tr>
                        <td id="choose">
                            <p style="margin-left: 365px; font-weight: bold;">Choose document type:</p>
                            <select class="browser-default" id="selectId" name="doc_type"
                                    onchange="onChangeSelect()">
                                <option value="pdf_type">pdf</option>
                                <option value="xls_type">xls</option>
                                <option value="csv_type">csv</option>
                            </select>
                        </td>
                    </tr>
                </table>
                </c:if>
                    <table>
                        <tr>
                            <td>
                                <c:if test="${not empty user}">
                                <form action="download" enctype="multipart/form-data" method="get" id="books_list">
                                    <input type="hidden" name="command" value="generate_books_list">
                                    <input type="hidden" id="inputTypeId" name="doc_type">
                                    <a href="javascript:{}"
                                       onclick="document.getElementById('books_list').submit(); return false;"
                                       class="waves-effect waves-light btn"
                                            >Generate books list <i class="fa fa-download" aria-hidden="true"></i></a>
                                </form>
                            </td>
                            <td>
                                <form action="download" enctype="multipart/form-data" method="get" id="users_list">
                                    <input type="hidden" name="command" value="generate_users_list">
                                    <input type="hidden" id="inputTypeId1" name="doc_type">
                                    <a href="javascript:{}"
                                       onclick="document.getElementById('users_list').submit(); return false;"
                                       class="waves-effect waves-light btn"
                                            >Generate users list <i class="fa fa-download" aria-hidden="true"></i></a>
                                </form>
                            </td>
                            <td>
                                <form action="download" enctype="multipart/form-data" method="get" id="books_info">
                                    <input type="hidden" name="command" value="generate_books_info">
                                    <input type="hidden" id="inputTypeId2" name="doc_type">
                                    <a href="javascript:{}"
                                       onclick="document.getElementById('books_info').submit(); return false;"
                                       class="waves-effect waves-light btn"
                                            >Generate books info <i class="fa fa-download" aria-hidden="true"></i></a>
                                </form>
                                </c:if>
                            </td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <td>
                                <c:if test="${not empty user}">
                                <form action="download" enctype="multipart/form-data" method="get" id="views_statistic">
                                    <input type="hidden" name="command" value="generate_views_statistic">
                                    <input type="hidden" id="inputTypeId3" name="doc_type">
                                    <a href="javascript:{}"
                                       onclick="document.getElementById('views_statistic').submit(); return false;"
                                       class="waves-effect waves-light btn"
                                            >Generate views statistic <i class="fa fa-download" aria-hidden="true"></i></a>
                                </form>
                            </td>
                            <td>
                                <form action="download" enctype="multipart/form-data" method="get"
                                      id="book_collections">
                                    <input type="hidden" name="command" value="generate_book_collections">
                                    <input type="hidden" id="inputTypeId4" name="doc_type">
                                    <a href="javascript:{}"
                                       onclick="document.getElementById('book_collections').submit(); return false;"
                                       class="waves-effect waves-light btn">Generate book collections of reader
                                        <i class="fa fa-download" aria-hidden="true"></i></a>
                                </form>
                                </c:if>
                            </td>
                        </tr>
                    </table>
            </div>
        </div>
    </div>

</main>


</body>
<%@include file='footer.jsp' %>
</html>