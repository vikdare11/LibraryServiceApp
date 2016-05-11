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

    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script type="text/javascript" src="assets/js/materialize.min.js"></script>
    <script src="assets/js/angular/angular.js"></script>
    <script type="text/javascript" src="assets/js/angular-ui-router.js"></script>
    <script type="text/javascript" src="assets/js/app/app.js"></script>
    <script type="text/javascript" src="assets/js/app/services.js"></script>
    <script type="text/javascript" src="assets/js/app/controllers.js"></script>
    <script type="text/javascript" src="assets/js/app/router.js"></script>

</head>
<body ng-app="myApp">
<div class="navbar-fixed">
    <nav>
        <div class="nav-wrapper">
            <a ui-sref="home" class="brand-logo center">Library Service</a>
            <ul class="right hide-on-med-and-down">

                <form ng-show="logged" method="post" action="logout.action" id="logout">
                    <input type="hidden" name="action">
                    <a ng-click="logout()" class="waves-effect waves-light btn"
                    >Logout <i class="fa fa-share" aria-hidden="true"></i></a>
                </form>

            </ul>
            <ul class="left hide-on-med-and-down">
                <div ng-show="false">
                    <form onClick="history.go(-1);return true;" enctype="multipart/form-data" method="get" id="back">

                        <a ui-sref="javascript:{}"
                           onclick="document.getElementById('back').submit(); return false;"
                           class="waves-effect waves-light btn"
                        >Back <i class="fa fa-reply" aria-hidden="true"></i></a>
                    </form>
                </div>
                <div ng-hide="logged">
                    <a ui-sref="login">Sign in</a>
                    <a ui-sref="registration">Sign up</a>
                <div/>
            </ul>
        </div>
    </nav>
</div>
<main>
    <ui-view></ui-view>
</main>

<footer class="page-footer" id="footer">
    <div class="container grey-text">
        <span class="left">Copyright 2016</span>
        <span class="right">Made by LibraryServiceCompany</span>
    </div>
</footer>
</body>
</html>