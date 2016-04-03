package dao;

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

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class UserDaoTest extends DBTestCase {

    private UserDao userDao = UserDaoImpl.getInstance();

    private FlatXmlDataSet dataSet;

    public UserDaoTest(String name) {
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
    public void testCreateIfUserNotExist() {
        User expectedUser = new User();
        expectedUser.setAdmin(false);
        expectedUser.setLogin("loginlogin");
        expectedUser.setPassword("passwordpassword");
        User actualUser = null;

        if (!userDao.isLoginExist(expectedUser.getLogin())) {
            int userId = userDao.create(expectedUser);
            expectedUser.setId(userId);
            actualUser = userDao.read(userId);
        }

        Assert.assertEquals(actualUser, expectedUser);
    }

    @Test
    public void testCreateIfUserWithTheSameLoginExist() {
        User expectedUser = new User();
        expectedUser.setAdmin(false);
        expectedUser.setLogin("login1");
        expectedUser.setPassword("passwordpassword");
        int userId = userDao.create(expectedUser);

        User actualUser = userDao.read(userId);

        Assert.assertNull(actualUser);
    }

    @Test
    public void testReadIfUserIdIsCorrect() {
        User expectedUser = new User();
        expectedUser.setAdmin(true);
        expectedUser.setId(1);
        expectedUser.setLogin("login1");
        expectedUser.setPassword("password1");

        User actualUser = userDao.read(expectedUser.getId());

        Assert.assertEquals(actualUser, expectedUser);
    }

    @Test
    public void testReadIfUserIdIsNotCorrect() {
        User actualUser = userDao.read(10);
        Assert.assertNull(actualUser);
    }

    @Test
    public void testUpdateIfUserExist() {
        User expectedUser = new User();
        expectedUser.setAdmin(true);
        expectedUser.setId(2);
        expectedUser.setLogin("login2");
        expectedUser.setPassword("password2");

        userDao.update(expectedUser);

        User actualUser = userDao.read(expectedUser.getId());

        Assert.assertEquals(actualUser, expectedUser);
    }

    @Test
    public void testUpdateIfUserNotExist() {
        User expectedUser = new User();
        expectedUser.setAdmin(true);
        expectedUser.setId(10);
        expectedUser.setLogin("login10");
        expectedUser.setPassword("password10");

        userDao.update(expectedUser);

        User actualUser = userDao.read(expectedUser.getId());

        Assert.assertNull(actualUser);
    }

    @Test
    public void testGetUsersList() {
        List<User> expectedUsers = new ArrayList<>();

        User expectedUser1 = new User();
        expectedUser1.setAdmin(true);
        expectedUser1.setId(1);
        expectedUser1.setLogin("login1");
        expectedUser1.setPassword("password1");
        expectedUsers.add(expectedUser1);

        User expectedUser2 = new User();
        expectedUser2.setAdmin(false);
        expectedUser2.setId(2);
        expectedUser2.setLogin("login2");
        expectedUser2.setPassword("password2");
        expectedUsers.add(expectedUser2);

        User expectedUser3 = new User();
        expectedUser3.setAdmin(false);
        expectedUser3.setId(3);
        expectedUser3.setLogin("login3");
        expectedUser3.setPassword("password3");
        expectedUsers.add(expectedUser3);

        User expectedUser4 = new User();
        expectedUser4.setAdmin(false);
        expectedUser4.setId(4);
        expectedUser4.setLogin("login4");
        expectedUser4.setPassword("password4");
        expectedUsers.add(expectedUser4);

        User expectedUser5 = new User();
        expectedUser5.setAdmin(false);
        expectedUser5.setId(5);
        expectedUser5.setLogin("login5");
        expectedUser5.setPassword("password5");
        expectedUsers.add(expectedUser5);

        List<User> actualUsers = userDao.getUsersList();

        Assert.assertEquals(actualUsers, expectedUsers);
    }

    @Test
    public void testDeleteIfUserExist() {
        User expectedUser = new User();
        expectedUser.setAdmin(false);
        expectedUser.setId(2);
        expectedUser.setLogin("login2");
        expectedUser.setPassword("password2");

        if (userDao.isLoginExist(expectedUser.getLogin())) {
            userDao.delete(expectedUser);
        }

        User actualUser = userDao.read(expectedUser.getId());

        Assert.assertNull(actualUser);
    }

    @Test
    public void testIsLoginExistIfLoginExist() {
        Assert.assertTrue(userDao.isLoginExist("login1"));
        Assert.assertTrue(userDao.isLoginExist("login2"));
        Assert.assertTrue(userDao.isLoginExist("login3"));
        Assert.assertTrue(userDao.isLoginExist("login4"));
        Assert.assertTrue(userDao.isLoginExist("login5"));
    }

    @Test
    public void testIsLoginExistIfLoginNotExist() {
        Assert.assertFalse(userDao.isLoginExist("login6"));
        Assert.assertFalse(userDao.isLoginExist("login7"));
        Assert.assertFalse(userDao.isLoginExist("login8"));
        Assert.assertFalse(userDao.isLoginExist("login9"));
        Assert.assertFalse(userDao.isLoginExist("login10"));
    }

    @Test
    public void findIdUserIfUserExist() {
        User expectedUser = new User();
        expectedUser.setAdmin(false);
        expectedUser.setLogin("login1");
        expectedUser.setPassword("password1");

        Assert.assertEquals(1, userDao.findIdUser(expectedUser));
    }

    @Test
    public void findIdUserIfUserNotExist() {
        User expectedUser = new User();
        expectedUser.setAdmin(false);
        expectedUser.setLogin("login10");
        expectedUser.setPassword("password10");

        Assert.assertEquals(-1, userDao.findIdUser(expectedUser));
    }
}
