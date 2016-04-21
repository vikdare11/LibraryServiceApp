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
    <table class="striped">
        <thead>
        <tr>
            <th>Login</th>
        </tr>
        </thead>
<c:forEach var="person" items="${users}">
        <tr>
    <td>
    <h5>${person.login}</h5>
    </td>
    <td>
        <form action="controller" enctype="multipart/form-data" method="get" id="view_inf">
            <input type="hidden" name="command" value="open_user">
            <input type="hidden" name="user_id" value="${person.id}">
            <a href="javascript:{}"
               onclick="document.getElementById('view_inf').submit(); return false;"
               class="waves-effect waves-light btn"
            ><i class="fa fa-eye" aria-hidden="true"></i></a>
        </form>

    </td>
    <c:if test="${user.admin == true}">
        <c:if test="${person.id != user.id && person.admin == false}">
            <td>
                <form action="controller" enctype="multipart/form-data" method="get" id="make_admin">
                    <input type="hidden" name="command" value="edit_user">
                    <input type="hidden" name="user_id" value="${person.id}">
                    <input type="hidden" name="isadmin" value=true>
                    <a href="javascript:{}"
                       onclick="document.getElementById('make_admin').submit(); return false;"
                       class="waves-effect waves-light btn"
                    >Make an admin <i class="fa fa-check" aria-hidden="true"></i></a>
                </form>

            </td>
        </c:if>
        <c:if test="${person.admin == true && person.login != 'admin'}">
            <td>
                <form action="controller" enctype="multipart/form-data" method="get" id="make_notadmin">
                    <input type="hidden" name="command" value="edit_user">
                    <input type="hidden" name="user_id" value="${person.id}">
                    <input type="hidden" name="isadmin" value=false>
                    <a href="javascript:{}"
                       onclick="document.getElementById('make_notadmin').submit(); return false;"
                       class="waves-effect waves-light btn"
                    >Make not admin <i class="fa fa-remove" aria-hidden="true"></i></a>
                </form>

            </td>
        </c:if>
        <c:if test="${person.login != 'admin'}">
            <td>
                <form action="controller" enctype="multipart/form-data" method="get" id="delete_user">
                    <input type="hidden" name="command" value="delete_user">
                    <input type="hidden" name="user_id" value="${person.id}">
                    <a href="javascript:{}"
                       onclick="if(confirm('Are you sure?')){document.getElementById('delete_user').submit();return true}else{return false;}"
                       class="waves-effect waves-light btn"
                    ><i class="fa fa-trash" aria-hidden="true"></i></a>
                </form>
            </td>
        </c:if>
    </c:if>
</c:forEach>
        </tr>
        </table>
    </main>

</body>
    <%@include file='footer.jsp'%>
</html>