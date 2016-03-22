package controller.command.implementation.author;

import controller.command.PostCommand;
import dao.AuthorDao;
import dao.implementation.AuthorDaoImpl;
import domain.Author;
import javax.servlet.http.HttpServletRequest;

public class OpenAuthorCommand implements PostCommand {

    private static final PostCommand instance = new OpenAuthorCommand();

    private OpenAuthorCommand(){}

    public static PostCommand getInstance() {
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
