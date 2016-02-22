package dao;

import domain.User;

import java.util.List;

/**
 * Created by Vika on 2/22/2016.
 */
public interface UserDao {
    int create(User user);
    User read(int idUser);
    void update(User user);
    void delete(User user);
    List<User> getUsersList();
}
