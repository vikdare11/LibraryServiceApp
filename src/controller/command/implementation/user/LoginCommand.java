package controller.command.implementation.user;

import controller.command.Command;
import controller.command.implementation.book.GetBooksCommand;
import domain.Reader;
import domain.User;
import service.Service;
import service.implementation.LoginService;

import javax.servlet.http.HttpServletRequest;

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
            return GetBooksCommand.getInstance().execute(request);
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
