package controller.command.implementation.author;

import controller.command.Command;
import dao.AuthorDao;
import dao.implementation.AuthorDaoImpl;
import domain.Author;

import javax.servlet.http.HttpServletRequest;

public class OpenEditAuthorCommand implements Command {
    private static OpenEditAuthorCommand ourInstance = new OpenEditAuthorCommand();

    public static OpenEditAuthorCommand getInstance() {
        return ourInstance;
    }

    private OpenEditAuthorCommand() {
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
