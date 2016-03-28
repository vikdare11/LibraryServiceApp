package controller.command.implementation.book;

import controller.command.Command;
import dao.PathDao;
import dao.implementation.PathDaoImpl;
import domain.Book;
import domain.Path;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Vika on 3/28/2016.
 */
public class ReadBookCommand implements Command {
    private static ReadBookCommand instance = new ReadBookCommand();

    private  ReadBookCommand(){}

    public static ReadBookCommand getInstance(){
        return instance;
    }
    @Override
    public String execute(HttpServletRequest request) {
        PathDao pathDao = PathDaoImpl.getInstance();

        int bookId = Integer.parseInt(request.getParameter("book_id"));
        Path path = pathDao.getPathsList("html", new Book(){{setId(bookId);}});

        String page = path.getPath();

        page = "WEB-INF/" + page;

        return page;
    }
}
