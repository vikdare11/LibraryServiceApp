<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:forEach var="user" items="${users}">
    <h3>${user.login}</h3>
    <form action="controller" enctype="multipart/form-data" method="post">
        <input type="hidden" name="command" value="open_user">
        <input type="hidden" name="user_id" value="${user.id}">
        <input type="submit" value="View information"/>
    </form>
    <form action="controller" enctype="multipart/form-data" method="post">
        <input type="hidden" name="command" value="delete_user">
        <input type="hidden" name="user_id" value="${user.id}">
        <input type="submit" value="Delete">
    </form>
</c:forEach>
</body>
</html>