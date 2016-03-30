package controller.command.implementation.book;

import controller.command.Command;
import dao.BookDao;
import dao.implementation.BookDaoImpl;
import domain.Book;

import javax.servlet.http.HttpServletRequest;

public class OpenEditBookCommand implements Command {
    private static OpenEditBookCommand ourInstance = new OpenEditBookCommand();

    public static OpenEditBookCommand getInstance() {
        return ourInstance;
    }

    private OpenEditBookCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        BookDao bookDao = BookDaoImpl.getInstance();
        int id = Integer.parseInt(request.getParameter("book_id"));
        Book book = bookDao.read(id);

        request.setAttribute("book", book);

        return "edit_book.jsp";
    }
}
