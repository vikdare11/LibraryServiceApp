package dao.implementation;

import dao.AuthorDao;
import dao.util.DbUtil;
import domain.Author;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vika on 2/22/2016.
 */
public class AuthorDaoImpl implements AuthorDao {

    @Override
    public int create(Author author) {
        return 0;
    }

    @Override
    public Author read(int idAuthor) {
        return null;
    }

    @Override
    public void update(Author author) {

    }

    @Override
    public void delete(Author author) {

    }

    @Override
    public List<Author> getAuthorsList() {
        List<Author> authors = new ArrayList<>();

        try(Connection connection = DbUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from author");
            while (resultSet.next()) {
                Author author = new Author();
                author.setName(resultSet.getString("name"));
                author.setId(resultSet.getInt("idauthor"));
                author.setSurname(resultSet.getString("surname"));

                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }
}
