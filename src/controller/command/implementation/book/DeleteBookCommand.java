package controller.command.implementation.book;

import controller.command.PostCommand;
import dao.BookDao;
import dao.implementation.BookDaoImpl;
import domain.Book;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Vika on 3/20/2016.
 */
public class DeleteBookCommand implements PostCommand {

    private static final PostCommand instance = new DeleteBookCommand();

    private DeleteBookCommand(){}

    public static PostCommand getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        BookDao bookDao = BookDaoImpl.getInstance();

        int id = Integer.parseInt(request.getParameter("book_id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Book book = new Book();
        book.setId(id);
        book.setName(title);
        book.setDescription(description);

        bookDao.delete(book);

        return GetBooksCommand.getInstance().execute(request);
    }
}
