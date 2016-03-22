package controller.command.implementation.book;

import controller.command.PostCommand;
import dao.BookDao;
import dao.implementation.BookDaoImpl;
import domain.Book;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetBooksCommand implements PostCommand {

    private static final PostCommand instance = new GetBooksCommand();

    private GetBooksCommand(){}

    public static PostCommand getInstance() {
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
