package controller.command.implementation.author;

import controller.command.Command;
import dao.AuthorDao;
import dao.implementation.AuthorDaoImpl;
import domain.Author;

import javax.servlet.http.HttpServletRequest;

public class EditAuthorCommand implements Command {
    private static final Command instance = new EditAuthorCommand();

    private EditAuthorCommand(){}

    public static Command getInstance() {
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

        if (!name.isEmpty() && !surname.isEmpty()) {
            authorDao.update(author);
        }

        return GetAuthorsCommand.getInstance().execute(request);
    }
}
