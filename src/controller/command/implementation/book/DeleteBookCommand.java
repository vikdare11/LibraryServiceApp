package controller.command.implementation.book;

import controller.command.Command;
import dao.BookDao;
import dao.implementation.BookDaoImpl;
import domain.Book;

import javax.servlet.http.HttpServletRequest;

public class DeleteBookCommand implements Command {

    private static final Command instance = new DeleteBookCommand();

    private DeleteBookCommand(){}

    public static Command getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        BookDao bookDao = BookDaoImpl.getInstance();

        int id = Integer.parseInt(request.getParameter("bookid"));

        Book book = new Book();
        book.setId(id);

        bookDao.delete(book);

        return GetBooksCommand.getInstance().execute(request);
    }
}
