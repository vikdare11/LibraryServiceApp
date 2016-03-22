package controller.command.implementation.user;

import controller.command.PostCommand;
import dao.UserDao;
import dao.implementation.UserDaoImpl;
import domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetUsersCommand implements PostCommand {

    private static final PostCommand instance = new GetUsersCommand();

    private GetUsersCommand(){}

    public static PostCommand getInstance() {
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
