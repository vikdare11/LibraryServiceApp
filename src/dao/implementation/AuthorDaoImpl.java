package dao.implementation;

import dao.AuthorDao;
import dao.util.DbUtil;
import domain.Author;
import domain.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {

    private static final AuthorDao instance = new AuthorDaoImpl();

    private AuthorDaoImpl() {}

    public static AuthorDao getInstance() {
        return instance;
    }

    @Override
    public int create(Author author) {
        int id = 0;
        if (!isNameExist(author.getName()) && !isSurnameExist(author.getSurname())) {
            try (Connection connection = DbUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO `author` (name, surname) " +
                         "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, author.getName());
                statement.setString(2, author.getSurname());
                statement.execute();
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        id = resultSet.getInt(1);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    @Override
    public Author read(int idAuthor) {
        Author author = null;
        try (Connection connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from `author` where idauthor=?")) {
            statement.setInt(1, idAuthor);

            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    author = new Author();
                    author.setSurname(resultSet.getString("surname"));
                    author.setName(resultSet.getString("name"));
                    author.setId(idAuthor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    @Override
    public void update(Author author) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("update `author` set surname=?, name=? "+
                     "where idauthor=?")) {
            statement.setString(1, author.getSurname());
            statement.setString(2, author.getName());
            statement.setInt(3, author.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Author author) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("delete from `author` where idauthor=?")){
            statement.setInt(1, author.getId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    @Override
    public Author getAuthorByBook(Book book) {
        Author author = null;
        int idBook = book.getId();
        try (Connection connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM author AS b JOIN bookofauthor AS ba ON b.idauthor = ba.idauthor JOIN book AS a ON ba.idbook = a.idbook WHERE a.idbook=?")) {
            statement.setInt(1, idBook);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    author = new Author();
                    author.setName(resultSet.getString("name"));
                    author.setId(resultSet.getInt("idauthor"));
                    author.setSurname(resultSet.getString("surname"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    @Override
    public boolean isNameExist(String name) {
        boolean nameExist = false;

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT name FROM `author` WHERE name=?"))
        {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    nameExist = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nameExist;
    }

    @Override
    public boolean isSurnameExist(String surname) {
        boolean surnameExist = false;

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT surname FROM `author` WHERE surname=?"))
        {
            statement.setString(1, surname);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    surnameExist = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return surnameExist;
    }
}
