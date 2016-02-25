package dao.implementation;

import dao.CommentDao;
import dao.util.DbUtil;
import domain.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    @Override
    public int create(Comment comment) {
        int id = 0;

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into `comment` (idreader, review, idbook) " +
                     "values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, comment.getIdReader());
            statement.setString(2, comment.getReview());
            statement.setInt(3, comment.getIdBook());
            statement.execute();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Comment read(int idComment) {
        Comment comment = null;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from `comment` where id=?")) {
            statement.setInt(1, idComment);

            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    comment = new Comment();
                    comment.setReview(resultSet.getString("review"));
                    comment.setIdBook(resultSet.getInt("idbook"));
                    comment.setIdReader(resultSet.getInt("idreader"));
                    comment.setId(idComment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comment;
    }

    @Override
    public void update(Comment comment) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("update `comment` set idbook=?, idreader=?, review=? "+
                     "where id=?")) {
            statement.setInt(1, comment.getIdBook());
            statement.setInt(2, comment.getIdReader());
            statement.setString(3, comment.getReview());
            statement.setInt(4, comment.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Comment comment) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("delete from `comment` where id=?")){
            statement.setInt(1, comment.getId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
