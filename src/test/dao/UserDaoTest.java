package dao;

import dao.implementation.UserDaoImpl;
import domain.User;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;

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
                "jdbc:mysql://localhost:3306/library?useUnicode=true&characterEncoding=UTF8&autoReconnect=true");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream(""));
        return dataSet;
    }

    @Test
    public void testCreateIfUserExist() {
        User expectedUser = new User();
        expectedUser.setAdmin(false);
        expectedUser.setLogin("login");
        expectedUser.setPassword("f5d6f85da351222ec20a69b31ec628de");

        int userId = userDao.create(expectedUser);
        expectedUser.setId(userId);

        User actualUser = userDao.read(userId);

        Assert.assertEquals(actualUser, expectedUser);
    }

}
