package controller.command.implementation.author;

import com.sun.deploy.net.HttpRequest;
import controller.command.Command;
import dao.AuthorDao;
import dao.implementation.AuthorDaoImpl;
import domain.Author;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Vika on 3/1/2016.
 */
public class GetAuthorsCommand implements Command {

    private static final Command instance = new GetAuthorsCommand();

    private GetAuthorsCommand(){}

    public static Command getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        AuthorDao authorDao = AuthorDaoImpl.getInstance();

        List<Author> authors = authorDao.getAuthorsList();

        request.setAttribute("authors", authors);

        return "authors.jsp";

    }
}
