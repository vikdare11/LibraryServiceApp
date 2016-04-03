package dao;

import dao.implementation.AuthorDaoImpl;
import domain.Author;
import domain.Book;
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

public class AuthorDaoTest extends DBTestCase {

    private AuthorDao authorDao = AuthorDaoImpl.getInstance();

    private IDataSet[] dataSets;

    public AuthorDaoTest(String name) {
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
        dataSets = new IDataSet[] { new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/author_data_set.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/bookofauthor_data_set.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/book_data_set.xml"))
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
    public void testCreateIfAuthorNotExist() {
        Author expectedAuthor = new Author();
        expectedAuthor.setName("name");
        expectedAuthor.setSurname("surname");
        Author actualAuthor = null;

        if (!authorDao.isNameExist(expectedAuthor.getName()) && !authorDao.isSurnameExist(expectedAuthor.getSurname())) {
            int authorId = authorDao.create(expectedAuthor);
            expectedAuthor.setId(authorId);
            actualAuthor = authorDao.read(authorId);
        }

        Assert.assertEquals(actualAuthor, expectedAuthor);
    }

    @Test
    public void testCreateIfAuthorWithTheSameNameAndSurnameExist() {
        Author expectedAuthor = new Author();
        expectedAuthor.setName("author_name1");
        expectedAuthor.setSurname("author_surname1");
        int authorId = authorDao.create(expectedAuthor);

        Author actualAuthor = authorDao.read(authorId);

        Assert.assertNull(actualAuthor);
    }

    @Test
    public void testReadIfAuthorIdIsCorrect() {
        Author expectedAuthor = new Author();
        expectedAuthor.setId(1);
        expectedAuthor.setName("author_name1");
        expectedAuthor.setSurname("author_surname1");

        Author actualAuthor = authorDao.read(expectedAuthor.getId());

        Assert.assertEquals(actualAuthor, expectedAuthor);
    }

    @Test
    public void testReadIfAuthorIdIsNotCorrect() {
        Author actualAuthor = authorDao.read(10);
        Assert.assertNull(actualAuthor);
    }

    @Test
    public void testUpdateIfAuthorExist() {
        Author expectedAuthor = new Author();
        expectedAuthor.setId(2);
        expectedAuthor.setName("new_author_name");
        expectedAuthor.setSurname("new_author_surname");

        authorDao.update(expectedAuthor);

        Author actualAuthor = authorDao.read(expectedAuthor.getId());

        Assert.assertEquals(actualAuthor, expectedAuthor);
    }

    @Test
    public void testUpdateIfAuthorNotExist() {
        Author expectedAuthor = new Author();
        expectedAuthor.setId(10);
        expectedAuthor.setName("author_name10");
        expectedAuthor.setSurname("author_surname10");

        authorDao.update(expectedAuthor);

        Author actualAuthor = authorDao.read(expectedAuthor.getId());

        Assert.assertNull(actualAuthor);
    }

    @Test
    public void testGetAuthorsList() {
        List<Author> expectedAuthors = new ArrayList<>();

        Author expectedAuthor1 = new Author();
        expectedAuthor1.setId(1);
        expectedAuthor1.setName("author_name1");
        expectedAuthor1.setSurname("author_surname1");
        expectedAuthors.add(expectedAuthor1);

        Author expectedAuthor2 = new Author();
        expectedAuthor2.setId(2);
        expectedAuthor2.setName("author_name2");
        expectedAuthor2.setSurname("author_surname2");
        expectedAuthors.add(expectedAuthor2);

        Author expectedAuthor3 = new Author();
        expectedAuthor3.setId(3);
        expectedAuthor3.setName("author_name3");
        expectedAuthor3.setSurname("author_surname3");
        expectedAuthors.add(expectedAuthor3);

        Author expectedAuthor4 = new Author();
        expectedAuthor4.setId(4);
        expectedAuthor4.setName("author_name4");
        expectedAuthor4.setSurname("author_surname4");
        expectedAuthors.add(expectedAuthor4);

        Author expectedAuthor5 = new Author();
        expectedAuthor5.setId(5);
        expectedAuthor5.setName("author_name5");
        expectedAuthor5.setSurname("author_surname5");
        expectedAuthors.add(expectedAuthor5);

        List<Author> actualAuthors = authorDao.getAuthorsList();

        Assert.assertEquals(actualAuthors, expectedAuthors);
    }

    @Test
    public void testGetAuthorByBookIfBookExist() {
        Book book = new Book();
        book.setId(1);
        Author expextedAuthor = new Author();
        expextedAuthor.setName("author_name1");
        expextedAuthor.setSurname("author_surname2");
        expextedAuthor.setId(1);

        Author actualAuthor = authorDao.getAuthorByBook(book);

        Assert.assertEquals(actualAuthor, expextedAuthor);
    }

    @Test
    public void testGetAuthorByBookIfBookNotExist() {
        Book book = new Book();
        book.setId(10);

        Author actualAuthor = authorDao.getAuthorByBook(book);

        Assert.assertNull(actualAuthor);
    }

    @Test
    public void testDeleteIfAuthorExist() {
        Author expectedAuthor = new Author();
        expectedAuthor.setId(2);
        expectedAuthor.setName("author_name2");
        expectedAuthor.setSurname("author_surname2");

        if (authorDao.isNameExist(expectedAuthor.getName())) {
            authorDao.delete(expectedAuthor);
        }

        Author actualAuthor = authorDao.read(expectedAuthor.getId());

        Assert.assertNull(actualAuthor);
    }

    @Test
    public void testIsNameExistIfNameExist() {
        Assert.assertTrue(authorDao.isNameExist("author_name1"));
        Assert.assertTrue(authorDao.isNameExist("author_name2"));
        Assert.assertTrue(authorDao.isNameExist("author_name3"));
        Assert.assertTrue(authorDao.isNameExist("author_name4"));
        Assert.assertTrue(authorDao.isNameExist("author_name5"));
    }

    @Test
    public void testIsNameExistIfNameNotExist() {
        Assert.assertFalse(authorDao.isNameExist("author_name6"));
        Assert.assertFalse(authorDao.isNameExist("author_name7"));
        Assert.assertFalse(authorDao.isNameExist("author_name8"));
        Assert.assertFalse(authorDao.isNameExist("author_name9"));
        Assert.assertFalse(authorDao.isNameExist("author_name10"));
    }

}