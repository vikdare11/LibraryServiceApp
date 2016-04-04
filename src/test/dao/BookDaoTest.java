package dao;

import dao.implementation.BookDaoImpl;
import domain.Book;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class BookDaoTest extends DBTestCase {
    private BookDao bookDao = BookDaoImpl.getInstance();

    private IDataSet[] dataSets;

    public BookDaoTest(String name) {
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

    @Test
    public void testCreateIfBookNotExist() {
        Book expectedBook = new Book();
        expectedBook.setName("titletitle");
        expectedBook.setDescription("descriptiondescription");
        expectedBook.setCountOfViews(0);
        expectedBook.setAuthor("author_name1 author_surname1");
        expectedBook.setIdAuthor(1);

        int bookId = bookDao.create(expectedBook);
        bookDao.insertBookAuthorLink(bookId, 1);
        expectedBook.setId(bookId);
        Book actualBook = bookDao.read(bookId);

        Assert.assertEquals(actualBook, expectedBook);
    }

    @Test
    public void testCreateIfBookWithTheSameTitleExist() {
        Book expectedBook = new Book();
        expectedBook.setName("title1");
        expectedBook.setDescription("descriptiondescription");
        int bookId = bookDao.create(expectedBook);

        Book actualBook = bookDao.read(bookId);

        Assert.assertNull(actualBook);
    }

    @Test
    public void testReadIfBookIdIsCorrect() {
        Book expectedBook = new Book();
        expectedBook.setId(1);
        expectedBook.setName("title1");
        expectedBook.setDescription("description1");
        expectedBook.setAuthor("author_name1 author_surname1");
        expectedBook.setCountOfViews(1);
        expectedBook.setIdAuthor(1);

        Book actualBook = bookDao.read(expectedBook.getId());

        Assert.assertEquals(actualBook, expectedBook);
    }

    @Test
    public void testReadIfBookIdIsNotCorrect() {
        Book actualBook = bookDao.read(10);
        Assert.assertNull(actualBook);
    }

    @Test
    public void testUpdateIfBookExist() {
        Book expectedBook = new Book();
        expectedBook.setId(2);
        expectedBook.setName("new_title");
        expectedBook.setDescription("new_description");
        expectedBook.setIdAuthor(2);
        expectedBook.setAuthor("author_name2 author_surname2");

        bookDao.update(expectedBook);

        Book actualBook = bookDao.read(expectedBook.getId());

        Assert.assertEquals(actualBook, expectedBook);
    }

    @Test
    public void testUpdateIfBookNotExist() {
        Book expectedBook = new Book();
        expectedBook.setId(10);
        expectedBook.setName("title10");
        expectedBook.setDescription("description10");

        bookDao.update(expectedBook);

        Book actualBook = bookDao.read(expectedBook.getId());

        Assert.assertNull(actualBook);
    }

    @Test
    public void testDeleteIfBookExist() {
        Book expectedBook = new Book();
        expectedBook.setId(2);
        expectedBook.setName("title2");
        expectedBook.setDescription("description2");
        expectedBook.setCountOfViews(2);

        if (bookDao.isTitleExist(expectedBook.getName())) {
            bookDao.delete(expectedBook);
        }

        Book actualUser = bookDao.read(expectedBook.getId());

        Assert.assertNull(actualUser);
    }

    @Test
    public void testGetBooksList() {
        List<Book> expectedBooks = new ArrayList<>();

        Book expectedBook1 = new Book();
        expectedBook1.setId(1);
        expectedBook1.setName("title1");
        expectedBook1.setDescription("description1");
        expectedBook1.setAuthor("author_name1 author_surname1");
        expectedBook1.setCountOfViews(1);
        expectedBook1.setIdAuthor(1);
        expectedBooks.add(expectedBook1);

        Book expectedBook2 = new Book();
        expectedBook2.setId(2);
        expectedBook2.setName("title2");
        expectedBook2.setDescription("description2");
        expectedBook2.setAuthor("author_name2 author_surname2");
        expectedBook2.setCountOfViews(2);
        expectedBook2.setIdAuthor(2);
        expectedBooks.add(expectedBook2);

        Book expectedBook3 = new Book();
        expectedBook3.setId(3);
        expectedBook3.setName("title3");
        expectedBook3.setDescription("description3");
        expectedBook3.setAuthor("author_name3 author_surname3");
        expectedBook3.setCountOfViews(3);
        expectedBook3.setIdAuthor(3);
        expectedBooks.add(expectedBook3);

        Book expectedBook4 = new Book();
        expectedBook4.setId(4);
        expectedBook4.setName("title4");
        expectedBook4.setDescription("description4");
        expectedBook4.setAuthor("author_name4 author_surname4");
        expectedBook4.setCountOfViews(4);
        expectedBook4.setIdAuthor(4);
        expectedBooks.add(expectedBook4);

        Book expectedBook5 = new Book();
        expectedBook5.setId(5);
        expectedBook5.setName("title5");
        expectedBook5.setDescription("description5");
        expectedBook5.setAuthor("author_name5 author_surname5");
        expectedBook5.setCountOfViews(5);
        expectedBook5.setIdAuthor(5);
        expectedBooks.add(expectedBook5);

        List<Book> actualBooks = bookDao.getBooksList();

        Assert.assertEquals(actualBooks, expectedBooks);
    }

    @Test
    public void testIsTitleExistIfTitleExist() {
        Assert.assertTrue(bookDao.isTitleExist("title1"));
        Assert.assertTrue(bookDao.isTitleExist("title2"));
        Assert.assertTrue(bookDao.isTitleExist("title3"));
        Assert.assertTrue(bookDao.isTitleExist("title4"));
        Assert.assertTrue(bookDao.isTitleExist("title5"));
    }

    @Test
    public void testIsTitleExistIfTitleNotExist() {
        Assert.assertFalse(bookDao.isTitleExist("title6"));
        Assert.assertFalse(bookDao.isTitleExist("title7"));
        Assert.assertFalse(bookDao.isTitleExist("title8"));
        Assert.assertFalse(bookDao.isTitleExist("title9"));
        Assert.assertFalse(bookDao.isTitleExist("title10"));
    }

    @Test
    public void testInsertBookAuthorLinkIfAuthorAndBookExist() {
        bookDao.insertBookAuthorLink(2, 3);
        Assert.assertTrue(bookDao.authorWroteBook(2, 3));
    }

    @Test
    public void testInsertBookAuthorLinkIfAuthorAndBookNotExist() {
        bookDao.insertBookAuthorLink(10, 10);
        Assert.assertFalse(bookDao.authorWroteBook(10, 10));
    }

    @Test
    public void testAddBookToReaderCollectionIfBookAndReaderExist() {
        bookDao.addBookToReaderCollection(4, 5);
        Assert.assertTrue(bookDao.readerHasBook(5, 4));
    }

    @Test
    public void testAddBookToReaderCollectionIfBookAndReaderNotExist() {
        bookDao.addBookToReaderCollection(10, 10);
        Assert.assertFalse(bookDao.readerHasBook(10, 10));
    }

    @Test
    public void testRemoveBookFromReaderCollectionIfBookAndReaderExist() {
        Assert.assertTrue(bookDao.readerHasBook(4, 4));
        bookDao.removeBookFromReaderCollection(4, 4);
        Assert.assertFalse(bookDao.readerHasBook(4, 4));
    }

    @Test
    public void testRemoveBookFromReaderCollectionIfBookAndReaderNotExist() {
        bookDao.removeBookFromReaderCollection(10, 10);
        Assert.assertFalse(bookDao.readerHasBook(10, 10));
    }

    @Test
    public void testReaderHasBookIfReaderHasBook() {
        Assert.assertTrue(bookDao.readerHasBook(1, 1));
        Assert.assertTrue(bookDao.readerHasBook(2, 2));
        Assert.assertTrue(bookDao.readerHasBook(3, 3));
        Assert.assertTrue(bookDao.readerHasBook(4, 4));
        Assert.assertTrue(bookDao.readerHasBook(5, 5));
    }

    @Test
    public void testReaderHasBookIfReaderHasNotBook() {
        Assert.assertFalse(bookDao.readerHasBook(1, 2));
        Assert.assertFalse(bookDao.readerHasBook(2, 3));
        Assert.assertFalse(bookDao.readerHasBook(3, 4));
        Assert.assertFalse(bookDao.readerHasBook(4, 5));
        Assert.assertFalse(bookDao.readerHasBook(5, 6));
    }

    @Test
    public void testAuthorWroteBookIfAuthorWroteBook() {
        Assert.assertTrue(bookDao.authorWroteBook(1, 1));
        Assert.assertTrue(bookDao.authorWroteBook(2, 2));
        Assert.assertTrue(bookDao.authorWroteBook(3, 3));
        Assert.assertTrue(bookDao.authorWroteBook(4, 4));
        Assert.assertTrue(bookDao.authorWroteBook(5, 5));
    }

    @Test
    public void testAuthorWroteBookIfAuthorDidNotWriteBook() {
        Assert.assertFalse(bookDao.authorWroteBook(1, 2));
        Assert.assertFalse(bookDao.authorWroteBook(2, 3));
        Assert.assertFalse(bookDao.authorWroteBook(3, 4));
        Assert.assertFalse(bookDao.authorWroteBook(4, 5));
        Assert.assertFalse(bookDao.authorWroteBook(5, 6));
    }
}
