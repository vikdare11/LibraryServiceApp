<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:if test="${not empty user}">
    <form action="controller" enctype="multipart/form-data" method="post">
        <input type="hidden" name="command" value="logout">
        <input type="submit" value="Logout">
    </form>
</c:if>
<c:forEach var="person" items="${users}">
    <h3>${person.login}</h3>
    <form action="controller" enctype="multipart/form-data" method="post">
        <input type="hidden" name="command" value="open_user">
        <input type="hidden" name="user_id" value="${person.id}">
        <input type="submit" value="View information"/>
    </form>
    <c:if test="${user.admin == true}">
    <form action="controller" enctype="multipart/form-data" method="post">
        <input type="hidden" name="command" value="delete_user">
        <input type="hidden" name="user_id" value="${person.id}">
        <input type="submit" value="Delete">
    </form>
    </c:if>
</c:forEach>
</body>
</html>