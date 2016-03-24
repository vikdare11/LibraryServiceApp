package controller.command.implementation.book;

import controller.command.Command;
import dao.AuthorDao;
import dao.BookDao;
import dao.implementation.AuthorDaoImpl;
import dao.implementation.BookDaoImpl;
import domain.Author;
import domain.Book;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetBooksByAuthorCommand implements Command {
    private static final Command instance = new GetBooksByAuthorCommand();

    private GetBooksByAuthorCommand(){}

    public static Command getInstance() {
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
