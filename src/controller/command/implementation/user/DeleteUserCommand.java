package controller.command.implementation.user;

import controller.command.Command;
import dao.UserDao;
import dao.implementation.UserDaoImpl;
import domain.User;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements Command {
    private static final Command instance = new DeleteUserCommand();

    private DeleteUserCommand(){}

    public static Command getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        UserDao bookDao = UserDaoImpl.getInstance();

        int id = Integer.parseInt(request.getParameter("user_id"));

        User user = new User();
        user.setId(id);

        bookDao.delete(user);

        return GetUsersCommand.getInstance().execute(request);
    }
}
