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
                            <form action="controller" enctype="multipart/form-data" method="get" id="the_form">
                                <input type="hidden" name="command" value="get_books">
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
                            <form action="controller" enctype="multipart/form-data" method="get" id="users">
                                <input type="hidden" name="command" value="get_users">
                                <a href="javascript:{}"
                                   onclick="document.getElementById('users').submit(); return false;"
                                   class="waves-effect waves-light btn"
                                        >Users <i class="fa fa-user" aria-hidden="true"></i></a>
                            </form>
                        </td>
                        <td>
                            <form action="controller" enctype="multipart/form-data" method="get" id="user">
                                <input type="hidden" name="command" value="open_user">
                                <input type="hidden" name="user_id" value="${user.id}">
                                <a href="javascript:{}"
                                   onclick="document.getElementById('user').submit(); return false;"
                                   class="waves-effect waves-light btn"
                                        >My profile <i class="fa fa-user-secret" aria-hidden="true"></i></a>
                            </form>
                            </c:if>
                        </td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <td>
                            <c:if test="${not empty user}">
                            <form action="download" enctype="multipart/form-data" method="get" id="pdf">
                                <input type="hidden" name="command" value="generate_pdf_documents">
                                <a href="javascript:{}"
                                   onclick="document.getElementById('pdf').submit(); return false;"
                                   class="waves-effect waves-light btn"
                                        >Generate pdf documents <i class="fa fa-download" aria-hidden="true"></i></a>
                            </form>
                        </td>
                        <td>
                            <form action="download" enctype="multipart/form-data" method="get" id="xls">
                                <input type="hidden" name="command" value="generate_xls_documents">
                                <a href="javascript:{}"
                                   onclick="document.getElementById('xls').submit(); return false;"
                                   class="waves-effect waves-light btn"
                                        >Generate xls documents <i class="fa fa-download" aria-hidden="true"></i></a>
                            </form>
                        </td>
                        <td>
                            <form action="download" enctype="multipart/form-data" method="get" id="csv">
                                <input type="hidden" name="command" value="generate_csv_documents">
                                <a href="javascript:{}"
                                   onclick="document.getElementById('csv').submit(); return false;"
                                   class="waves-effect waves-light btn"
                                        >Generate csv documents <i class="fa fa-download" aria-hidden="true"></i></a>
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
