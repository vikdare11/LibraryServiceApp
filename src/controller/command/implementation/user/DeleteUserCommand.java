package controller.command.implementation.user;

import controller.command.PostCommand;
import dao.UserDao;
import dao.implementation.UserDaoImpl;
import domain.User;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements PostCommand {
    private static final PostCommand instance = new DeleteUserCommand();

    private DeleteUserCommand(){}

    public static PostCommand getInstance() {
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
