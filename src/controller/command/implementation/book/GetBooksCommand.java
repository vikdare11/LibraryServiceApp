package controller.command.implementation.book;

import controller.command.Command;
import dao.BookDao;
import dao.implementation.BookDaoImpl;
import domain.Book;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetBooksCommand implements Command {

    private static final Command instance = new GetBooksCommand();

    private GetBooksCommand(){}

    public static Command getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        BookDao bookDao = BookDaoImpl.getInstance();

        List<Book> books = bookDao.getBooksList();

        request.setAttribute("books", books);

        return "books.jsp";

    }
}
