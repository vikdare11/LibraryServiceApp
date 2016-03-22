package controller.command.implementation.book;

import controller.command.PostCommand;
import domain.BookViewObject;
import service.Service;
import service.implementation.GetBookInfoService;

import javax.servlet.http.HttpServletRequest;

public class ViewBookCommand implements PostCommand {

    private static final PostCommand instance = new ViewBookCommand();

    private ViewBookCommand() {}

    public static PostCommand getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Integer bookId = Integer.parseInt(request.getParameter(("bookid")));
        Service<Integer, BookViewObject> getBookInfoService = GetBookInfoService.getInstance();
        BookViewObject book = getBookInfoService.execute(bookId);

        request.setAttribute("bookVO", book);

        return "book_view.jsp";
    }
}
