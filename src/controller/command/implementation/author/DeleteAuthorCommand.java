package controller.command.implementation.author;

import controller.command.Command;
import dao.AuthorDao;
import dao.implementation.AuthorDaoImpl;
import domain.Author;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Vika on 3/1/2016.
 */
public class DeleteAuthorCommand implements Command {

    private static final Command instance = new DeleteAuthorCommand();

    private DeleteAuthorCommand(){}

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

        authorDao.delete(author);

        return GetAuthorsCommand.getInstance().execute(request);
    }
}