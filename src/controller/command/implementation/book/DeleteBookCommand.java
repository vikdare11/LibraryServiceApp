package controller.command.implementation.book;

import controller.command.Command;
import dao.BookDao;
import dao.PathDao;
import dao.implementation.BookDaoImpl;
import dao.implementation.PathDaoImpl;
import domain.Book;
import domain.Path;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DeleteBookCommand implements Command {

    private static final Command instance = new DeleteBookCommand();

    private DeleteBookCommand(){}

    public static Command getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        BookDao bookDao = BookDaoImpl.getInstance();
        PathDao pathDao = PathDaoImpl.getInstance();

        int id = Integer.parseInt(request.getParameter("bookid"));

        Book book = new Book();
        book.setId(id);

        Path path = pathDao.getPathsList("html", book);
        deleteBookFile(path.getPath(), request);

        path = pathDao.getPathsList("fb2", book);
        deleteBookFile(path.getPath(), request);

        path = pathDao.getPathsList("pdf", book);
        deleteBookFile(path.getPath(), request);

        path = pathDao.getPathsList("txt", book);
        deleteBookFile(path.getPath(), request);

        pathDao.delete(new Path(){{setIdBook(id);}});
        bookDao.delete(book);

        return GetBooksCommand.getInstance().execute(request);
    }

    private void deleteBookFile(String file, HttpServletRequest request) {
        String filePath = request.getServletContext().getRealPath("WEB-INF/" + file);
        try {
            Files.delete(Paths.get(filePath));
        } catch (IOException e) {

        }
    }
}
