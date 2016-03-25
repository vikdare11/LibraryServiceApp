package controller.command.implementation.comment;

import controller.command.Command;
import controller.command.implementation.book.ViewBookCommand;
import dao.CommentDao;
import dao.implementation.CommentDaoImpl;
import domain.Comment;
import domain.User;

import javax.servlet.http.HttpServletRequest;

public class AddCommentCommand implements Command {
    private static AddCommentCommand ourInstance = new AddCommentCommand();

    public static AddCommentCommand getInstance() {
        return ourInstance;
    }

    private AddCommentCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        CommentDao commentDao = CommentDaoImpl.getInstance();

        int readerId = ((User)request.getSession().getAttribute("user")).getId();
        int idBook = Integer.parseInt(request.getParameter("book_id"));
        String review = request.getParameter("review");

        Comment comment = new Comment();
        comment.setIdBook(idBook);
        comment.setReview(review);
        comment.setIdReader(readerId);


        commentDao.create(comment);
        request.setAttribute("bookid", idBook);
        return ViewBookCommand.getInstance().execute(request);
    }
}
