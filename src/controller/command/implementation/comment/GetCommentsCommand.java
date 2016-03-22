package controller.command.implementation.comment;

import controller.command.PostCommand;
import dao.CommentDao;
import dao.implementation.CommentDaoImpl;
import domain.Comment;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetCommentsCommand implements PostCommand {
    private static GetCommentsCommand ourInstance = new GetCommentsCommand();

    public static GetCommentsCommand getInstance() {
        return ourInstance;
    }

    private GetCommentsCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        CommentDao commentDao = CommentDaoImpl.getInstance();

        int idBook = Integer.parseInt(request.getParameter("book_id"));

        List<Comment> comments = commentDao.getCommentsByBookId(idBook);

        request.setAttribute("comments", comments);

        return "book_view.jsp";

    }
}
