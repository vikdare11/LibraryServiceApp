package dao.implementation;

import dao.PathDao;
import dao.util.DbUtil;
import domain.Path;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vika on 2/22/2016.
 */
public class PathDaoImpl implements PathDao {

    @Override
    public int create(Path path) {
        return 0;
    }

    @Override
    public Path read(int idPath) {
        return null;
    }

    @Override
    public void update(Path path) {

    }

    @Override
    public void delete(Path path) {

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
