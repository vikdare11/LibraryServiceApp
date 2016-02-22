package dao.implementation;

import dao.BookDao;
import dao.util.DbUtil;
import domain.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vika on 2/22/2016.
 */
public class BookDaoImpl implements BookDao {
    @Override
    public int create(Book book) {
        return 0;
    }

    @Override
    public Book read(int idBook) {
        return null;
    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void delete(Book book) {

    }

    @Override
    public List<Book> getBooksList() {
        List<Book> books = new ArrayList<>();

        try(Connection connection = DbUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from book");
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("idbook"));
                book.setName(resultSet.getString("name"));
                book.setIdAuthor(resultSet.getInt("idauthor"));
                book.setDescription(resultSet.getString("description"));
                book.setCountOfViews(resultSet.getInt("countOfViews"));

                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
