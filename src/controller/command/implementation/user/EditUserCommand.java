package controller.command.implementation.user;

import controller.command.PostCommand;
import dao.UserDao;
import dao.implementation.UserDaoImpl;
import domain.User;

import javax.servlet.http.HttpServletRequest;

public class EditUserCommand implements PostCommand {

    private static EditUserCommand instance = new EditUserCommand();

    public static EditUserCommand getInstance() {
        return instance;
    }

    private EditUserCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        UserDao userDao = UserDaoImpl.getInstance();

        int id = Integer.parseInt(request.getParameter("user_id"));
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        boolean isAdmin = Boolean.valueOf(request.getParameter("isadmin"));

        User user = new User();
        user.setId(id);
        user.setPassword(password);
        user.setLogin(login);
        user.setAdmin(isAdmin);

        userDao.update(user);

        return GetUsersCommand.getInstance().execute(request);
    }

}
