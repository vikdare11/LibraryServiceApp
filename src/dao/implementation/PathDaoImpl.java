package dao.implementation;

import dao.PathDao;
import dao.util.DbUtil;
import domain.Book;
import domain.Path;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PathDaoImpl implements PathDao {

    private static final PathDao instance = new PathDaoImpl();

    private PathDaoImpl() {}

    public static PathDao getInstance() {
        return instance;
    }

    @Override
    public int create(Path path) {
        int id = -1;
        if (!isPathExist(path.getPath())) {
            try (Connection connection = DbUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO `path` (path, `format`, idbook) " +
                         "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, path.getPath());
                statement.setString(2, path.getFormat());
                statement.setInt(3, path.getIdBook());
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
    public Path read(int idPath) {
        Path path = null;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from `path` where idpath=?")) {
            statement.setInt(1, idPath);

            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    path = new Path();
                    path.setFormat(resultSet.getString("format"));
                    path.setPath(resultSet.getString("path"));
                    path.setIdBook(resultSet.getInt(("idbook")));
                    path.setId(idPath);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return path;
    }

    @Override
    public void update(Path path) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("update `path` set path=?, format=?, idbook=? "+
                     "where idpath=?")) {
            statement.setString(1, path.getPath());
            statement.setString(2, path.getFormat());
            statement.setInt(3, path.getIdBook());
            statement.setInt(4, path.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Path path) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("delete from `path` where idbook=?")){
            statement.setInt(1, path.getIdBook());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Path> getPathsList(Book book) {
        List<Path> paths = new ArrayList<>();

        try(Connection connection = DbUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from path where idbook=" + book.getId());
            while (resultSet.next()) {
                Path path = new Path();
                path.setId(resultSet.getInt("idpath"));
                path.setIdBook(book.getId());
                path.setFormat(resultSet.getString("format"));
                path.setPath(resultSet.getString("path"));

                paths.add(path);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paths;
    }

    @Override
    public Path getPathsList(String format, Book book) {
        Path path = null;
        try(Connection connection = DbUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from path where idbook=" + book.getId()
                    + " and `format`='" + format + "'");
            if (resultSet.next()) {
                path = new Path();
                path.setId(resultSet.getInt("idpath"));
                path.setIdBook(book.getId());
                path.setFormat(format);
                path.setPath(resultSet.getString("path"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return path;
    }

    @Override
    public int getPathIdByFormatAndBookId(String format, int bookId) {
        int id = -1;
        try (Connection connection = DbUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT idpath FROM path WHERE idbook=? AND format=?")) {
            statement.setInt(1, bookId);
            statement.setString(2, format);

            try (ResultSet resultSet = statement.executeQuery()) {
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
    public boolean isPathExist(String path) {
        boolean pathExist = false;

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT path FROM `path` WHERE path=?")) {
            statement.setString(1, path);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    pathExist = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pathExist;
    }
}
