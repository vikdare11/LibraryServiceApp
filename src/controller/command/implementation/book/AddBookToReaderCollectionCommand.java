package controller.command.implementation.book;

import controller.command.Command;
import controller.command.implementation.user.ViewUserCommand;
import dao.BookDao;
import dao.ReaderDao;
import dao.implementation.BookDaoImpl;
import dao.implementation.ReaderDaoImpl;
import domain.User;

import javax.servlet.http.HttpServletRequest;

public class AddBookToReaderCollectionCommand implements Command {
    private static AddBookToReaderCollectionCommand ourInstance = new AddBookToReaderCollectionCommand();

    public static AddBookToReaderCollectionCommand getInstance() {
        return ourInstance;
    }

    private AddBookToReaderCollectionCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        BookDao bookDao = BookDaoImpl.getInstance();
        ReaderDao readerDao = ReaderDaoImpl.getInstance();
        int idUser = ((User)request.getSession().getAttribute("user")).getId();
        int idReader = readerDao.getReaderByUserId(idUser);
        int idBook = Integer.parseInt(request.getParameter("book_id"));

        if (!bookDao.readerHasBook(idReader, idBook)) {
            bookDao.addBookToReaderCollection(idBook, idReader);
        }

        return ViewUserCommand.getInstance().execute(request);
    }
}
