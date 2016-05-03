package controller.command.implementation.book;

import controller.command.Command;
import dao.BookDao;
import dao.implementation.BookDaoImpl;
import domain.User;

import javax.servlet.http.HttpServletRequest;

public class RemoveBookFromReaderCollectionCommand implements Command {
    private static RemoveBookFromReaderCollectionCommand ourInstance = new RemoveBookFromReaderCollectionCommand();

    public static RemoveBookFromReaderCollectionCommand getInstance() {
        return ourInstance;
    }

    private RemoveBookFromReaderCollectionCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        BookDao bookDao = BookDaoImpl.getInstance();

        int idReader = ((User)request.getSession().getAttribute("user")).getId();
        int idBook = Integer.parseInt(request.getParameter("bookid"));

        if (bookDao.readerHasBook(idReader, idBook)) {
            bookDao.removeBookFromReaderCollection(idReader, idBook);
        }

        return GetBooksCommand.getInstance().execute(request);

    }
}
