package dao;

import dao.implementation.ReaderDaoImpl;
import domain.Reader;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ReaderDaoTest extends DBTestCase {
    private ReaderDao readerDao = ReaderDaoImpl.getInstance();

    private IDataSet[] dataSets;

    public ReaderDaoTest(String name) {
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
        dataSets = new IDataSet[] { new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/book_data_set.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/author_data_set.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/bookofauthor_data_set.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/user_data_set.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/reader_data_set.xml")),

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
    public void testCreateIfReaderNotExist() {
        Reader expectedReader = new Reader();
        expectedReader.setEmail("email6@domain.com");
        expectedReader.setIdUser(6);
        int readerId = readerDao.create(expectedReader);
        expectedReader.setId(readerId);
        Reader actualReader = readerDao.read(6);
        Assert.assertEquals(actualReader, expectedReader);
    }

    @Test
    public void testCreateIfReaderWithTheSameEmailExist() {
        Reader expectedReader = new Reader();
        expectedReader.setEmail("email1@domain.com");
        expectedReader.setIdUser(1);
        int readerId = readerDao.create(expectedReader);
        Reader actualReader = null;

        if (!(readerId == 0)) {
            actualReader = readerDao.read(readerId);
        }

        Assert.assertNull(actualReader);
    }

    @Test
    public void testReadIfReaderIdIsCorrect() {
        Reader expectedReader = new Reader();
        expectedReader.setId(1);
        expectedReader.setEmail("email1@domain.com");
        expectedReader.setIdUser(1);

        Reader actualReader = readerDao.read(expectedReader.getIdUser());

        Assert.assertEquals(actualReader, expectedReader);
    }

    @Test
    public void testReadIfReaderIdIsNotCorrect() {
        Reader actualReader = readerDao.read(10);
        Assert.assertNull(actualReader);
    }

    @Test
    public void testUpdateIfReaderExist() {
        Reader expectedReader = new Reader();
        expectedReader.setId(2);
        expectedReader.setEmail("newemail@domain.com");
        expectedReader.setIdUser(2);

        readerDao.update(expectedReader);

        Reader actualReader = readerDao.read(expectedReader.getId());

        Assert.assertEquals(actualReader, expectedReader);
    }

    @Test
    public void testUpdateIfReaderNotExist() {
        Reader expectedReader = new Reader();
        expectedReader.setId(10);
        expectedReader.setEmail("email10@domain.com");
        expectedReader.setIdUser(10);

        readerDao.update(expectedReader);

        Reader actualReader = readerDao.read(expectedReader.getId());

        Assert.assertNull(actualReader);
    }

    @Test
    public void testDeleteIfReaderExist() {
        Reader expectedReader = new Reader();
        expectedReader.setId(2);
        expectedReader.setEmail("email2@domain.com");
        expectedReader.setIdUser(2);

        if (readerDao.isEmailExist(expectedReader.getEmail())) {
            readerDao.delete(expectedReader);
        }

        Reader actualReader = readerDao.read(expectedReader.getId());

        Assert.assertNull(actualReader);
    }
}
