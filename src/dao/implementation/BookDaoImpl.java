package dao.implementation;

import dao.BookDao;
import dao.util.DbUtil;
import domain.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    @Override
    public int create(Book book) {
        int id = 0;

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into `book` (title, description, countOfViews) " +
                     "values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, book.getName());
            statement.setString(2, book.getDescription());
            statement.setInt(3, book.getCountOfViews());
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
    public Book read(int idBook) {
        Book book = null;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from `book` where id=?")) {
            statement.setInt(1, idBook);

            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    book = new Book();
                    book.setCountOfViews(resultSet.getInt("countOfViews"));
                    book.setDescription(resultSet.getString("description"));
                    book.setName(resultSet.getString("title"));
                    book.setId(idBook);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public void update(Book book) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("update `book` set title=?, description=?, countOfViews=? "+
                     "where id=?")) {
            statement.setString(1, book.getName());
            statement.setString(2, book.getDescription());
            statement.setInt(3, book.getCountOfViews());
            statement.setInt(4, book.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Book book) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("delete from `book` where id=?")){
            statement.setInt(1, book.getId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
