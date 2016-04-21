package controller.command.implementation.book;

import controller.command.Command;
import dao.BookDao;
import dao.implementation.BookDaoImpl;
import domain.User;

import javax.servlet.http.HttpServletRequest;

public class RemoveBookFromReaderCollection implements Command {
    private static RemoveBookFromReaderCollection ourInstance = new RemoveBookFromReaderCollection();

    public static RemoveBookFromReaderCollection getInstance() {
        return ourInstance;
    }

    private RemoveBookFromReaderCollection() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        BookDao bookDao = BookDaoImpl.getInstance();

        int idReader = ((User)request.getSession().getAttribute("user")).getId();
        int idBook = Integer.parseInt(request.getParameter("bookid"));

        if (bookDao.readerHasBook(idReader, idBook)) {
            bookDao.removeBookFromReaderCollection(idReader, idBook);
        }

        //request.setAttribute("user_id", idReader);

        return "controller?command=open_user&user_id="+idReader;

    }
}
