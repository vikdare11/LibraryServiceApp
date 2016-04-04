package service;

import dao.UserDao;
import dao.implementation.UserDaoImpl;
import domain.User;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Test;
import service.implementation.LoginService;

import java.io.FileInputStream;

public class LoginServiceTest extends DBTestCase{

    private UserDao userDao = UserDaoImpl.getInstance();

    private FlatXmlDataSet dataSet;

    public LoginServiceTest(String name) {
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
        dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/user_data_set.xml"));
        return dataSet;
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
        User expectedUser = new User();
        expectedUser.setLogin("login3");
        expectedUser.setPassword("password3");
        expectedUser.setId(userDao.findIdUser(expectedUser));
        Service<User, User> loginService = LoginService.getInstance();

        User actualUser = loginService.execute(expectedUser);

        Assert.assertEquals(actualUser, expectedUser);
    }

    public void testExecuteIfUserNotExist() {
        User expectedUser = new User();
        expectedUser.setLogin("login10");
        expectedUser.setPassword("password10");
        expectedUser.setId(userDao.findIdUser(expectedUser));
        Service<User, User> loginService = LoginService.getInstance();

        User actualUser = loginService.execute(expectedUser);

        Assert.assertNull(actualUser);
    }
}
