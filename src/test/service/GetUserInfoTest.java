package test.service;

import dao.ReaderDao;
import dao.UserDao;
import dao.implementation.ReaderDaoImpl;
import dao.implementation.UserDaoImpl;
import domain.*;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Test;
import service.Service;
import service.implementation.GetUserInfoService;

import java.io.FileInputStream;
import java.util.List;

public class GetUserInfoTest extends DBTestCase {
    private UserDao userDao = UserDaoImpl.getInstance();
    private ReaderDao readerDao = ReaderDaoImpl.getInstance();
    Service<Integer, UserViewObject> getUserInfoService = GetUserInfoService.getInstance();

    private IDataSet[] dataSets;

    public GetUserInfoTest(String name) {
        super(name);
        System.setProperty(
                PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
                "com.mysql.jdbc.Driver");
        System.setProperty(
                PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
                "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF8&autoReconnect=true");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        dataSets = new IDataSet[] { new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/user_data_set.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/reader_data_set.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/bookcollectionofreader_data_set.xml"))
        };
        return new CompositeDataSet(dataSets);
    }

    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.REFRESH;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL;
    }

    @Test
    public void testExecuteIfUserExist() {

        User user = userDao.read(1);
        Reader reader = readerDao.read(1);
        List<Book> bookCollectionOfReader = readerDao.getBookCollection(reader.getId());

        UserViewObject expectedUserViewObject = new UserViewObject();
        expectedUserViewObject.setBookCollection(bookCollectionOfReader);
        expectedUserViewObject.setEmail(reader.getEmail());
        expectedUserViewObject.setLogin(user.getLogin());

        UserViewObject actualUserViewObject = getUserInfoService.execute(1);

        Assert.assertEquals(expectedUserViewObject,actualUserViewObject);
    }

    public void testExecuteIfUserNotExist() {
        UserViewObject actualUserViewObject = getUserInfoService.execute(10);

        Assert.assertNull(actualUserViewObject);
    }
}
