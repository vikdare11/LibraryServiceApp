package controller.command.implementation.comment;

import controller.command.PostCommand;
import dao.CommentDao;
import dao.implementation.CommentDaoImpl;
import domain.Comment;

import javax.servlet.http.HttpServletRequest;

public class DeleteCommentCommand implements PostCommand {
    private static DeleteCommentCommand ourInstance = new DeleteCommentCommand();

    public static DeleteCommentCommand getInstance() {
        return ourInstance;
    }

    private DeleteCommentCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        CommentDao commentDao = CommentDaoImpl.getInstance();

        int id = Integer.parseInt(request.getParameter("comment_id"));
        Comment comment = new Comment();
        comment.setId(id);

        commentDao.delete(comment);

        return GetCommentsCommand.getInstance().execute(request);
    }
}
