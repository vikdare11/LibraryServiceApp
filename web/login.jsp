<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vika
  Date: 3/17/2016
  Time: 5:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<form method="post" action="controller">
  <input type="hidden" name="command" value="login">
  <input type="text" maxlength="45" required name="login" placeholder="Login">
  <input type="password" maxlength="45" required name="password" placeholder="Password">
  <input type="submit" value="Login">
</form>
<c:if test="${not empty param['message']}">
  <p>Invalid login or password</p>
</c:if>
<h1></h1>
<a href="registration.jsp">Sign up</a>
</body>
</html>
