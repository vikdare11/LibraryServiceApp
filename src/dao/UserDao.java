package dao;

import domain.User;

import java.util.List;

public interface UserDao {
    int create(User user);
    User read(int idUser);
    void update(User user);
    void delete(User user);
    List<User> getUsersList();
}
