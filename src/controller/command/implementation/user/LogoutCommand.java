package controller.command.implementation.user;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    private static final LogoutCommand instance = new LogoutCommand();

    private LogoutCommand(){}

    public static LogoutCommand getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request)  {

        HttpSession session = request.getSession(true);
        session.removeAttribute("user");
        session.removeAttribute("admin");
        session.invalidate();

        return "login.jsp";
    }
}
