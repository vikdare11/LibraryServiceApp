package service.implementation;

import dao.ReaderDao;
import dao.UserDao;
import dao.implementation.ReaderDaoImpl;
import dao.implementation.UserDaoImpl;
import domain.*;
import service.Service;

public class RegistrationService implements Service<Registration, Boolean> {
    private static final RegistrationService instance = new RegistrationService();

    private RegistrationService(){}

    public static RegistrationService getInstance() {
        return instance;
    }

    @Override
    public Boolean execute(Registration registrationData) {
        UserDao userDao;
        ReaderDao readerDao;

        userDao = UserDaoImpl.getInstance();
        readerDao = ReaderDaoImpl.getInstance();

        User user = new User();
        user.setLogin(registrationData.getLogin());
        user.setPassword(registrationData.getPassword());

        Reader reader = new Reader();
        reader.setEmail(registrationData.getEmail());
        try {
            if (userDao.isLoginExist(user.getLogin())) {
                return false;
            }
            int id = userDao.create(user);
            user.setId(id);
            userDao.create(user);
            reader.setIdUser(id);
            readerDao.create(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
