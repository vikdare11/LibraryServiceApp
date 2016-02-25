package dao;

import domain.Comment;

import java.util.List;

public interface CommentDao {
    int create(Comment comment);
    Comment read(int idComment);
    void update(Comment comment);
    void delete(Comment comment);
    List<Comment> getCommentsList();
}
