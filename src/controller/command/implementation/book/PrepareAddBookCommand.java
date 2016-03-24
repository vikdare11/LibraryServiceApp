package controller.command.implementation.book;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class PrepareAddBookCommand implements Command {
    private static PrepareAddBookCommand ourInstance = new PrepareAddBookCommand();

    public static PrepareAddBookCommand getInstance() {
        return ourInstance;
    }

    private PrepareAddBookCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("author_id"));
        request.setAttribute("author_id", id);

        return "add_book.jsp";
    }
}
