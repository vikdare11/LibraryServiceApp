<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<FORM><INPUT Type="button" VALUE="Back" onClick="history.go(-1);return true;"></FORM>

<a href="index.jsp">Main page</a>
    <form action="controller" enctype="multipart/form-data" method="get">
        <input type="hidden" name="command" value="logout">
        <input type="submit" value="Logout">
    </form>
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
    <form action="controller" enctype="multipart/form-data" method="get">
        <input type="hidden" name="command" value="delete_user">
        <input type="hidden" name="user_id" value="${person.id}">
        <input type="submit" value="Delete">
    </form>
    </c:if>
</c:forEach>
</body>
</html>