<%--
  Created by IntelliJ IDEA.
  User: Vika
  Date: 3/17/2016
  Time: 3:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<FORM><INPUT Type="button" VALUE="Back" onClick="history.go(-1);return true;"></FORM>

<a href="index.jsp">Main page</a>
<form method="post" action="controller">
  <input type="hidden" name="command" value="register">
  <input type="text" required name="login" placeholder="Login">
  <input type="password" maxlength="45" required name="password" placeholder="Password">
  <input type="email" maxlength="45" required name="email" placeholder="user@library.lib">
  <input type="submit" value="Register">
</form>
<h1></h1>
<a href="login.jsp">Sign in</a>
<c:if test="${not empty param['message']}">
  <p>User with this login is already exist</p>
</c:if>
</body>
</html>
