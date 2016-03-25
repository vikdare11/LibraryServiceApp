package service.implementation;

import dao.UserDao;
import dao.implementation.UserDaoImpl;
import domain.User;
import service.Service;

public class LoginService implements Service<User, User> {

    private static final LoginService instance = new LoginService();

    private LoginService(){}

    public static LoginService getInstance() {
        return instance;
    }

    @Override
    public User execute(User user) {
        User foundUser = null;
        UserDao userDao;
        userDao = UserDaoImpl.getInstance();

        try {
            int idUser = userDao.findIdUser(user);
            if (idUser != -1) {
                foundUser = userDao.read(idUser);
            }

        } catch (Exception e) {
            e.printStackTrace();;
        }

        return foundUser;
    }
}
