package controller.command.implementation.author;

import controller.command.PostCommand;
import dao.AuthorDao;
import dao.implementation.AuthorDaoImpl;
import domain.Author;

import javax.servlet.http.HttpServletRequest;

public class EditAuthorCommand implements PostCommand {
    private static final PostCommand instance = new EditAuthorCommand();

    private EditAuthorCommand(){}

    public static PostCommand getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        AuthorDao authorDao = AuthorDaoImpl.getInstance();

        int id = Integer.parseInt(request.getParameter("author_id"));
        String surname = request.getParameter("author_surname");
        String name = request.getParameter("author_name");

        Author author = new Author();
        author.setId(id);
        author.setName(name);
        author.setSurname(surname);

        authorDao.update(author);

        return GetAuthorsCommand.getInstance().execute(request);
    }
}
