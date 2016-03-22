package controller.command.implementation.comment;

import controller.command.PostCommand;
import dao.CommentDao;
import dao.implementation.CommentDaoImpl;
import domain.Comment;

import javax.servlet.http.HttpServletRequest;

public class AddCommentCommand implements PostCommand {
    private static AddCommentCommand ourInstance = new AddCommentCommand();

    public static AddCommentCommand getInstance() {
        return ourInstance;
    }

    private AddCommentCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        CommentDao commentDao = CommentDaoImpl.getInstance();

        int id = Integer.parseInt(request.getParameter("comment_id"));
        int idReader = Integer.parseInt(request.getParameter("reader_id"));
        int idBook = Integer.parseInt(request.getParameter("book_id"));
        String review = request.getParameter("review");

        Comment comment = new Comment();
        comment.setId(id);
        comment.setIdBook(idBook);
        comment.setReview(review);
        comment.setIdReader(idReader);
        commentDao.create(comment);

        return GetCommentsCommand.getInstance().execute(request);
    }
}
