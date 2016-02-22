package dao;

import domain.Comment;

import java.util.List;

/**
 * Created by Vika on 2/22/2016.
 */
public interface CommentDao {
    int create(Comment comment);
    Comment read(int idComment);
    void update(Comment comment);
    void delete(Comment comment);
    List<Comment> getCommentsList();
}
