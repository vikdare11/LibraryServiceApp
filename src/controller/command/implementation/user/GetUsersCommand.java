package controller.command.implementation.user;

import controller.command.Command;
import dao.UserDao;
import dao.implementation.UserDaoImpl;
import domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetUsersCommand implements Command {

    private static final Command instance = new GetUsersCommand();

    private GetUsersCommand(){}

    public static Command getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        UserDao userDao = UserDaoImpl.getInstance();

        List<User> users = userDao.getUsersList();

        request.setAttribute("users", users);

        return "users.jsp";

    }
}
