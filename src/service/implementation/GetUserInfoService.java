package service.implementation;

import dao.ReaderDao;
import dao.UserDao;
import dao.implementation.ReaderDaoImpl;
import dao.implementation.UserDaoImpl;
import domain.Book;
import domain.Reader;
import domain.User;
import domain.UserViewObject;
import service.Service;

import java.util.List;

public class GetUserInfoService implements Service <Integer, UserViewObject>{
    private static GetUserInfoService ourInstance = new GetUserInfoService();

    public static GetUserInfoService getInstance() {
        return ourInstance;
    }

    private GetUserInfoService() { }

    @Override
    public UserViewObject execute(Integer userId) {
        UserDao userDao = UserDaoImpl.getInstance();
        ReaderDao readerDao = ReaderDaoImpl.getInstance();

        User user = userDao.read(userId);
        Reader reader = readerDao.read(userId);

        if (reader == null && user == null) {
            return null;
        }
        List<Book> bookCollectionOfReader = readerDao.getBookCollection(reader.getId());

        UserViewObject userViewObject = new UserViewObject();
        userViewObject.setBookCollection(bookCollectionOfReader);
        userViewObject.setEmail(reader.getEmail());
        userViewObject.setLogin(user.getLogin());
        userViewObject.setIsAdmin(user.isAdmin());

        return userViewObject;
    }
}
