package dao.implementation;

import dao.CommentDao;
import dao.util.DbUtil;
import domain.Comment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vika on 2/22/2016.
 */
public class CommentDaoImpl implements CommentDao {
    @Override
    public int create(Comment comment) {
        return 0;
    }

    @Override
    public Comment read(int idComment) {
        return null;
    }

    @Override
    public void update(Comment comment) {

    }

    @Override
    public void delete(Comment comment) {

    }

    @Override
    public List<Comment> getCommentsList() {
        List<Comment> comments = new ArrayList<>();

        try(Connection connection = DbUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from comment");
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("idcomment"));
                comment.setIdBook(resultSet.getInt("idbook"));
                comment.setIdReader(resultSet.getInt("idreader"));
                comment.setReview(resultSet.getString("review"));

                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }
}
