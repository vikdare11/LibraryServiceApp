package controller.command.implementation.author;

import controller.command.Command;
import dao.AuthorDao;
import dao.implementation.AuthorDaoImpl;
import domain.Author;

import javax.servlet.http.HttpServletRequest;

public class AddAuthorCommand implements Command {
    private static final Command instance = new AddAuthorCommand();

    private AddAuthorCommand(){}

    public static Command getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        AuthorDao authorDao = AuthorDaoImpl.getInstance();

        String surname = request.getParameter("author_surname");
        String name = request.getParameter("author_name");

        Author author = new Author();
        author.setName(name);
        author.setSurname(surname);

        if (!surname.isEmpty() && !name.isEmpty()){

            authorDao.create(author);
        } else
            OpenAuthorCommand.getInstance().execute(request);

        return GetAuthorsCommand.getInstance().execute(request);
    }
}
