package dao.implementation;

import dao.UserDao;
import dao.util.DbUtil;
import domain.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vika on 2/23/2016.
 */
public class UserDaoImpl implements UserDao {
    @Override
    public int create(User user) {
        return 0;
    }

    @Override
    public User read(int idUser) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public List<User> getUsersList() {
        List<User> users = new ArrayList<>();

        try(Connection connection = DbUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("iduser"));
                user.setAdmin(resultSet.getBoolean("isadmin"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
