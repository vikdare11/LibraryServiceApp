<div class="navbar-fixed">
    <nav>
        <div class="nav-wrapper">
            <a href="index.jsp" class="brand-logo center">Library Service</a>
            <ul class="right hide-on-med-and-down">
                <c:if test="${not empty user}">
                    <form method="post" action="logout.action" id="logout">
                        <input type="hidden" name="action">
                        <a href="javascript:{}"
                           onclick="document.getElementById('logout').submit(); return false;"
                           class="waves-effect waves-light btn"
                        >Logout <i class="fa fa-share" aria-hidden="true"></i></a>
                    </form>

            </ul>
            <ul class="left hide-on-med-and-down">
                <form  onClick="history.go(-1);return true;" enctype="multipart/form-data" method="get" id="back">

                    <a href="javascript:{}"
                       onclick="document.getElementById('back').submit(); return false;"
                       class="waves-effect waves-light btn"
                    >Back <i class="fa fa-reply" aria-hidden="true"></i></a>
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
