package controller.command;

import domain.Reader;
import domain.User;
import service.Service;
import service.implementation.LoginService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Vika on 3/17/2016.
 */
public class LoginCommand implements Command {

    private static final LoginCommand instance = new LoginCommand();

    private LoginCommand(){}

    public static LoginCommand getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String page;

        if (validateRequest(request)) {

            User user = new User();
            user.setLogin(request.getParameter("login"));
            user.setPassword(request.getParameter("password"));
            Service<User, Reader> loginService = LoginService.getInstance();
            Reader reader = null;
            try {
                reader = loginService.execute(user);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (reader == null) {
                return "login.jsp" + "?" + "message" + "=incorrect";
            }

            if (user.getAdmin()) {
                request.getSession(true).setAttribute("admin", user);
            }
            else {
                request.getSession(true).setAttribute("user", user);
            }
            page = "index.jsp";
        }
        else {
            page = "login.jsp";
        }

        return page;
    }

    private boolean validateRequest(HttpServletRequest request) {
        if ((!request.getParameter("login").isEmpty()) &&
                (!request.getParameter("password").isEmpty())) {
            return true;
        }
        else {
            return false;
        }
    }
}
