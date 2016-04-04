package test.service;

import dao.AuthorDao;
import dao.BookDao;
import dao.CommentDao;
import dao.PathDao;
import dao.implementation.AuthorDaoImpl;
import dao.implementation.BookDaoImpl;
import dao.implementation.CommentDaoImpl;
import dao.implementation.PathDaoImpl;
import domain.*;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Test;
import service.implementation.GetBookInfoService;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class GetBookInfoServiceTest extends DBTestCase {

    private GetBookInfoService getBookInfoService = GetBookInfoService.getInstance();

    private CommentDao commentDao = CommentDaoImpl.getInstance();
    private PathDao pathDao = PathDaoImpl.getInstance();
    private BookDao bookDao = BookDaoImpl.getInstance();
    private AuthorDao authorDao = AuthorDaoImpl.getInstance();
    private IDataSet[] dataSets;

    public GetBookInfoServiceTest(String name) {
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
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/path_data_set.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/user_data_set.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/reader_data_set.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/comment_data_set.xml"))
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
    public void testExecuteIfBookExist() {
        Book book = bookDao.read(1);
        List<Comment> listOfComments = commentDao.getCommentsByBookId(1);
        Author author = authorDao.getAuthorByBook(book);
        Path readPath = pathDao.getPathsList("html", book);
        List<Path> downloadPaths = new ArrayList<>();
        downloadPaths.add(pathDao.getPathsList("fb2", book));
        downloadPaths.add(pathDao.getPathsList("pdf", book));
        downloadPaths.add(pathDao.getPathsList("txt", book));

        BookViewObject expectedBookViewObject = new BookViewObject();
        expectedBookViewObject.setAuthor(author);
        expectedBookViewObject.setBook(book);
        expectedBookViewObject.setListOfComments(listOfComments);
        expectedBookViewObject.setReadPath(readPath);
        expectedBookViewObject.setDownloadPaths(downloadPaths);
        book.setCountOfViews(5);
        bookDao.update(book);
        book.setCountOfViews(book.getCountOfViews()+1);

        BookViewObject actualBookViewObject = getBookInfoService.execute(1);

        Assert.assertEquals(expectedBookViewObject, actualBookViewObject);
    }

    public void testExecuteIfBookNotExist() {
        BookViewObject actualBookViewObject = getBookInfoService.execute(10);

        Assert.assertNull(actualBookViewObject);
    }
}
