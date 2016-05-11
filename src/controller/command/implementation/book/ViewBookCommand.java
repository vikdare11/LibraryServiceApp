package controller.command.implementation.book;

import controller.command.Command;
import domain.BookViewObject;
import service.Service;
import service.implementation.GetBookInfoService;

import javax.servlet.http.HttpServletRequest;

public class ViewBookCommand implements Command {

    private static final Command instance = new ViewBookCommand();

    private ViewBookCommand() {}

    public static Command getInstance() {
        return instance;
    }



    @Override
    public String execute(HttpServletRequest request) {
        Integer bookId = null;
        try {
            bookId = Integer.parseInt(request.getParameter(("bookid")));
        } catch (Exception e) {
            bookId = (Integer) request.getAttribute(("bookid"));
        }
        Service<Integer, BookViewObject> getBookInfoService = GetBookInfoService.getInstance();
        BookViewObject book = getBookInfoService.execute(bookId);

        request.setAttribute("bookVO", book);

        return "book_view.jsp";
    }
}
