package dao.implementation;

import dao.PathDao;
import dao.util.DbUtil;
import domain.Path;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PathDaoImpl implements PathDao {

    @Override
    public int create(Path path) {
        int id = 0;

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into `path` (path, format, idbook) " +
                     "values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, path.getPath());
            statement.setString(2, path.getFormat());
            statement.setInt(3, path.getId());
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
    public Path read(int idPath) {
        Path path = null;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from `path` where id=?")) {
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
             PreparedStatement statement = connection.prepareStatement("update `reader` set path=?, format=?, idbook=? "+
                     "where id=?")) {
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
             PreparedStatement statement = connection.prepareStatement("delete from `path` where id=?")){
            statement.setInt(1, path.getId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Path> getPathsList() {
        List<Path> paths = new ArrayList<>();

        try(Connection connection = DbUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from path");
            while (resultSet.next()) {
                Path path = new Path();
                path.setId(resultSet.getInt("idpath"));
                path.setIdBook(resultSet.getInt("idbook"));
                path.setFormat(resultSet.getNString("format"));
                path.setPath(resultSet.getString("path"));

                paths.add(path);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paths;
    }
}
