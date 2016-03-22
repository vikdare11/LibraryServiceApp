package service.implementation;

import dao.CommentDao;
import dao.implementation.CommentDaoImpl;
import domain.Comment;
import service.Service;

public class AddCommentService implements Service<Comment, Integer> {

    private static final AddCommentService instance = new AddCommentService();

    private AddCommentService(){}

    public static AddCommentService getInstance() {
        return instance;
    }

    @Override
    public Integer execute(Comment comment) {
        CommentDao commentDao = CommentDaoImpl.getInstance();
        return commentDao.create(comment);
    }
}
