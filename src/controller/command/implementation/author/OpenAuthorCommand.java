package controller.command.implementation.author;

import controller.command.Command;
import dao.AuthorDao;
import dao.implementation.AuthorDaoImpl;
import domain.Author;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by Vika on 3/1/2016.
 */
public class OpenAuthorCommand implements Command {

    private static final Command instance = new OpenAuthorCommand();

    private OpenAuthorCommand(){}

    public static Command getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        AuthorDao authorDao = AuthorDaoImpl.getInstance();
        int id = Integer.parseInt(request.getParameter("author_id"));
        Author author = authorDao.read(id);

        request.setAttribute("author", author);

        return "edit_author.jsp";

    }
}
