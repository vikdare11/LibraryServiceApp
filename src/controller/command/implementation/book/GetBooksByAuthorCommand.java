package controller.command.implementation.book;

import controller.command.PostCommand;
import dao.AuthorDao;
import dao.BookDao;
import dao.implementation.AuthorDaoImpl;
import dao.implementation.BookDaoImpl;
import domain.Author;
import domain.Book;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Vika on 3/20/2016.
 */
public class GetBooksByAuthorCommand implements PostCommand {
    private static final PostCommand instance = new GetBooksByAuthorCommand();

    private GetBooksByAuthorCommand(){}

    public static PostCommand getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {

        AuthorDao authorDao = AuthorDaoImpl.getInstance();
        int id = Integer.parseInt(request.getParameter("author_id"));
        Author author = authorDao.read(id);

        request.setAttribute("author", author);

        BookDao bookDao = BookDaoImpl.getInstance();
        List<Book> books = bookDao.getBooksList();

        request.setAttribute("books", books);

        return "author.jsp";

    }
}
