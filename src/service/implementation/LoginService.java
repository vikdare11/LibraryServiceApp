package service.implementation;

import dao.ReaderDao;
import dao.UserDao;
import dao.implementation.ReaderDaoImpl;
import dao.implementation.UserDaoImpl;
import domain.Reader;
import domain.User;
import service.Service;

/**
 * Created by Vika on 3/17/2016.
 */
public class LoginService implements Service<User, Reader> {

    private static final LoginService instance = new LoginService();

    private LoginService(){}

    public static LoginService getInstance() {
        return instance;
    }

    @Override
    public Reader execute(User user) {
        Reader reader = null;

        ReaderDao readerDao;
        UserDao userDao;
        readerDao = ReaderDaoImpl.getInstance();
        userDao = UserDaoImpl.getInstance();

        try {
            int idUser = userDao.findIdUser(user);
            if (idUser != -1) {
                reader = readerDao.read(idUser);
            }

        } catch (Exception e) {
            e.printStackTrace();;
        }

        return reader;
    }
}
