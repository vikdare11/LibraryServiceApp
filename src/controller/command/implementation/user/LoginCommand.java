package controller.command.implementation.user;

import controller.command.Command;
import dao.UserDao;
import dao.implementation.UserDaoImpl;
import domain.User;
import org.apache.commons.codec.digest.DigestUtils;
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

            UserDao userDao = UserDaoImpl.getInstance();
            User user = new User();
            user.setLogin(request.getParameter("login"));
            user.setPassword(DigestUtils.md5Hex(request.getParameter("password")));
            user.setId(userDao.findIdUser(user));

            Service<User, User> loginService = LoginService.getInstance();
            User foundUser = null;

            try {
                foundUser = loginService.execute(user);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (foundUser == null) {
                return "login.jsp" + "?" + "message" + "=incorrect";
            }

            request.getSession(true).setAttribute("user", foundUser);

            return "index.jsp";
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
