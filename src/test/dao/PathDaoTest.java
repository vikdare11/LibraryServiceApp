package dao;

import dao.implementation.PathDaoImpl;
import domain.Book;
import domain.Path;
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

public class PathDaoTest extends DBTestCase {
    private PathDao pathDao = PathDaoImpl.getInstance();

    private IDataSet[] dataSets;

    public PathDaoTest(String name) {
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
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/user_data_set.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/reader_data_set.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/bookofauthor_data_set.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/path_data_set.xml"))
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
    public void testCreateIfPathNotExist() {
        Path expectedPath = new Path();
        expectedPath.setFormat("ttf");
        expectedPath.setPath("path");
        expectedPath.setIdBook(5);
        Path actualPath = null;

        if (!pathDao.isPathExist(expectedPath.getPath())) {
            int pathId = pathDao.create(expectedPath);
            expectedPath.setId(pathId);
            actualPath = pathDao.read(pathId);
        }

        Assert.assertEquals(actualPath, expectedPath);
    }

    @Test
    public void testCreateIfPathWithTheSameTitleAndFormatExist() {
        Path expectedPath = new Path();
        expectedPath.setFormat("pdf");
        expectedPath.setPath("Book/path3.pdf");
        expectedPath.setIdBook(1);
        int pathId = pathDao.create(expectedPath);
        Path actualPath = null;
        if (!(pathId == -1)) {
            actualPath = pathDao.read(pathId);
        }

        Assert.assertNull(actualPath);
    }

    @Test
    public void testReadIfPathIdIsCorrect() {
        Path expectedPath = new Path();
        expectedPath.setId(3);
        expectedPath.setFormat("pdf");
        expectedPath.setPath("Book/path3.pdf");
        expectedPath.setIdBook(1);

        Path actualPath = pathDao.read(expectedPath.getId());

        Assert.assertEquals(actualPath, expectedPath);
    }

    @Test
    public void testReadIfPathIdIsNotCorrect() {
        Path actualPath = pathDao.read(10);
        Assert.assertNull(actualPath);
    }

    @Test
    public void testUpdateIfPathExist() {
        Path expectedPath = new Path();
        expectedPath.setId(3);
        expectedPath.setFormat("newpdf");
        expectedPath.setPath("Book/path3.pdf");
        expectedPath.setIdBook(1);

        pathDao.update(expectedPath);

        Path actualPath = pathDao.read(expectedPath.getId());

        Assert.assertEquals(actualPath, expectedPath);
    }

    @Test
    public void testUpdateIfPathNotExist() {
        Path expectedPath = new Path();
        expectedPath.setId(10);
        expectedPath.setFormat("ttf");
        expectedPath.setPath("Book/path10.ttf");
        expectedPath.setIdBook(1);

        pathDao.update(expectedPath);

        Path actualPath = pathDao.read(expectedPath.getId());

        Assert.assertNull(actualPath);
    }

    @Test
    public void testDeleteIfPathExist() {
        Path expectedPath = new Path();
        expectedPath.setId(3);
        expectedPath.setFormat("pdf");
        expectedPath.setPath("Book/path3.pdf");
        expectedPath.setIdBook(1);

        if (pathDao.isPathExist(expectedPath.getPath())) {
            pathDao.delete(expectedPath);
        }

        Path actualUser = pathDao.read(expectedPath.getId());

        Assert.assertNull(actualUser);
    }

    @Test
    public void testGetPathsList() {
        List<Path> expectedPaths = new ArrayList<>();
        Book book = new Book();

        book.setCountOfViews(1);
        book.setDescription("description1");
        book.setName("title1");
        book.setId(1);
        book.setIdAuthor(1);

        Path expectedPath1 = new Path();
        expectedPath1.setId(1);
        expectedPath1.setFormat("html");
        expectedPath1.setPath("Book/path1.html");
        expectedPath1.setIdBook(1);
        expectedPaths.add(expectedPath1);

        Path expectedPath2 = new Path();
        expectedPath2.setId(2);
        expectedPath2.setFormat("fb2");
        expectedPath2.setPath("Book/path2.fb2");
        expectedPath2.setIdBook(1);
        expectedPaths.add(expectedPath2);

        Path expectedPath3 = new Path();
        expectedPath3.setId(3);
        expectedPath3.setFormat("pdf");
        expectedPath3.setPath("Book/path3.pdf");
        expectedPath3.setIdBook(1);
        expectedPaths.add(expectedPath3);

        Path expectedPath4 = new Path();
        expectedPath4.setId(4);
        expectedPath4.setFormat("txt");
        expectedPath4.setPath("Book/path4.txt");
        expectedPath4.setIdBook(1);
        expectedPaths.add(expectedPath4);

        List<Path> actualPaths = pathDao.getPathsList(book);

        Assert.assertEquals(actualPaths, expectedPaths);
    }

    @Test
    public void testGetPathIdByFormatAndBookId() {
        Path expectedPath = new Path();
        expectedPath.setId(1);
        expectedPath.setFormat("html");
        expectedPath.setIdBook(1);

        int actualPath = pathDao.getPathIdByFormatAndBookId(expectedPath.getFormat(), expectedPath.getIdBook());

        Assert.assertEquals(actualPath, expectedPath.getId());
    }

    @Test
    public void testIsPathExistIfPathExist() {
        Assert.assertTrue(pathDao.isPathExist("Book/path1.html"));
        Assert.assertTrue(pathDao.isPathExist("Book/path2.fb2"));
        Assert.assertTrue(pathDao.isPathExist("Book/path3.pdf"));
        Assert.assertTrue(pathDao.isPathExist("Book/path4.txt"));
    }

    @Test
    public void testIsPathExistIfPathNotExist() {
        Assert.assertFalse(pathDao.isPathExist("Book/path5.html"));
        Assert.assertFalse(pathDao.isPathExist("Book/path6.fb2"));
        Assert.assertFalse(pathDao.isPathExist("Book/path7.pdf"));
        Assert.assertFalse(pathDao.isPathExist("Book/path8.txt"));
    }
}

