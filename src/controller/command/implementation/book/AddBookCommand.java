package controller.command.implementation.book;

import controller.command.Command;
import dao.BookDao;
import dao.PathDao;
import dao.implementation.BookDaoImpl;
import dao.implementation.PathDaoImpl;
import domain.Book;
import domain.Path;

import javax.servlet.http.HttpServletRequest;

public class AddBookCommand implements Command {
    private static final Command instance = new AddBookCommand();

    private AddBookCommand(){}

    public static Command getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        BookDao bookDao = BookDaoImpl.getInstance();

        int authorId = Integer.parseInt(request.getParameter("author_id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Book book = new Book();
        book.setId(authorId);
        book.setName(title);
        book.setDescription(description);

        int bookId = bookDao.create(book);

        Path path = new Path();
        path.setIdBook(bookId);
        String downloadPath = request.getParameter("downloadPath");
        path.setPath(downloadPath);
        path.setFormat(downloadPath.split("\\.")[1]);
        PathDao pathDao = PathDaoImpl.getInstance();
        pathDao.create(path);

        bookDao.insertBookAuthorLink(bookId, authorId);
        return GetBooksCommand.getInstance().execute(request);
    }
}
