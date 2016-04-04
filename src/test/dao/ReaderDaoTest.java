package dao;

import dao.implementation.ReaderDaoImpl;
import domain.Book;
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

    public void testGetReadersList() {
        List<Reader> expectedReaders = new ArrayList<>();

        Reader expectedReader1 = new Reader();
        expectedReader1.setId(1);
        expectedReader1.setEmail("email1@domain.com");
        expectedReader1.setIdUser(1);
        expectedReaders.add(expectedReader1);

        Reader expectedReader2 = new Reader();
        expectedReader2.setId(2);
        expectedReader2.setEmail("email2@domain.com");
        expectedReader2.setIdUser(2);
        expectedReaders.add(expectedReader2);

        Reader expectedReader3 = new Reader();
        expectedReader3.setId(3);
        expectedReader3.setEmail("email3@domain.com");
        expectedReader3.setIdUser(3);
        expectedReaders.add(expectedReader3);

        Reader expectedReader4 = new Reader();
        expectedReader4.setId(4);
        expectedReader4.setEmail("email4@domain.com");
        expectedReader4.setIdUser(4);
        expectedReaders.add(expectedReader4);

        Reader expectedReader5 = new Reader();
        expectedReader5.setId(5);
        expectedReader5.setEmail("email5@domain.com");
        expectedReader5.setIdUser(5);
        expectedReaders.add(expectedReader5);

        List<Reader> actualReaders = readerDao.getReadersList();

        Assert.assertEquals(actualReaders, expectedReaders);
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

    @Test
    public void testGetBookCollectionIfReaderExist() {
        List<Book> expectedBookCollectionOfReader = new ArrayList<>();

        Book book = new Book();
        book.setId(1);
        book.setName("title1");
        book.setDescription("description1");
        book.setAuthor("author_name1 author_surname1");
        expectedBookCollectionOfReader.add(book);

        List<Book> actualBookCollectionOfReader = readerDao.getBookCollection(1);

        Assert.assertEquals(actualBookCollectionOfReader, expectedBookCollectionOfReader);
    }

    @Test
    public void testGetBookCollectionIfReaderNotExist() {

        List<Book> expectedBookCollectionOfReader = new ArrayList<>();
        List<Book> actualBookCollectionOfReader = readerDao.getBookCollection(10);

        Assert.assertEquals(actualBookCollectionOfReader, expectedBookCollectionOfReader);
    }

    @Test
    public void testGetReaderIdByUserIdIfUserExist() {
        Assert.assertEquals(1, readerDao.getReaderIdByUserId(1));
        Assert.assertEquals(2, readerDao.getReaderIdByUserId(2));
        Assert.assertEquals(3, readerDao.getReaderIdByUserId(3));
        Assert.assertEquals(4, readerDao.getReaderIdByUserId(4));
        Assert.assertEquals(5, readerDao.getReaderIdByUserId(5));
    }

    @Test
    public void testGetReaderIdByUserIdIfUserNotExist() {
        Assert.assertEquals(-1, readerDao.getReaderIdByUserId(7));
        Assert.assertEquals(-1, readerDao.getReaderIdByUserId(8));
        Assert.assertEquals(-1, readerDao.getReaderIdByUserId(9));
        Assert.assertEquals(-1, readerDao.getReaderIdByUserId(10));
        Assert.assertEquals(-1, readerDao.getReaderIdByUserId(11));
    }

    @Test
    public void testIsEmailExistIfEmailExist() {
        Assert.assertTrue(readerDao.isEmailExist("email1@domain.com"));
        Assert.assertTrue(readerDao.isEmailExist("email2@domain.com"));
        Assert.assertTrue(readerDao.isEmailExist("email3@domain.com"));
        Assert.assertTrue(readerDao.isEmailExist("email4@domain.com"));
        Assert.assertTrue(readerDao.isEmailExist("email5@domain.com"));
    }

    @Test
    public void testIsEmailExistIfEmailNotExist() {
        Assert.assertFalse(readerDao.isEmailExist("email6@domain.com"));
        Assert.assertFalse(readerDao.isEmailExist("email7@domain.com"));
        Assert.assertFalse(readerDao.isEmailExist("email8@domain.com"));
        Assert.assertFalse(readerDao.isEmailExist("email9@domain.com"));
        Assert.assertFalse(readerDao.isEmailExist("email10@domain.com"));
    }
}
