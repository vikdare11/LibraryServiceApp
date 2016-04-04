package dao;

import dao.implementation.CommentDaoImpl;
import domain.Comment;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.*;

public class CommentDaoTest extends DBTestCase {
    private CommentDao commentDao = CommentDaoImpl.getInstance();

    private IDataSet[] dataSets;

    public CommentDaoTest(String name) {
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
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/user_data_set.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/reader_data_set.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/comment_data_set.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/author_data_set.xml")),
                new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/dataset/bookofauthor_data_set.xml"))
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
    public void testCreateIfCommentNotExist() {
        Comment expectedComment = new Comment();
        expectedComment.setIdBook(1);
        expectedComment.setIdReader(3);
        expectedComment.setReview("reviewreview");

        int commentId = commentDao.create(expectedComment);
        expectedComment.setId(commentId);
        Comment actualComment = commentDao.read(commentId);
        Assert.assertEquals(actualComment, expectedComment);
    }

    @Test
    public void testReadIfCommentIdIsCorrect() {
        Comment expectedComment = new Comment();
        expectedComment.setId(1);
        expectedComment.setIdBook(1);
        expectedComment.setIdReader(1);
        expectedComment.setReview("review1");

        Comment actualComment = commentDao.read(expectedComment.getId());

        Assert.assertEquals(actualComment, expectedComment);
    }

    @Test
    public void testReadIfCommentIdIsNotCorrect() {
        Comment actualComment = commentDao.read(30);
        Assert.assertNull(actualComment);
    }

    @Test
    public void testUpdateIfCommentExist() {
        Comment expectedComment = new Comment();
        expectedComment.setId(2);
        expectedComment.setIdBook(2);
        expectedComment.setIdReader(2);
        expectedComment.setReview("new_review");

        commentDao.update(expectedComment);

        Comment actualComment = commentDao.read(expectedComment.getId());

        Assert.assertEquals(actualComment, expectedComment);
    }

    @Test
    public void testUpdateIfCommentNotExist() {
        Comment expectedComment = new Comment();
        expectedComment.setId(30);
        expectedComment.setIdBook(10);
        expectedComment.setIdReader(10);
        expectedComment.setReview("review10");

        commentDao.update(expectedComment);

        Comment actualComment = commentDao.read(expectedComment.getId());

        Assert.assertNull(actualComment);
    }

    @Test
    public void testDeleteIfCommentExist() {
        Comment expectedComment = new Comment();
        expectedComment.setId(2);
        expectedComment.setIdBook(2);
        expectedComment.setIdReader(2);
        expectedComment.setReview("review2");

        commentDao.delete(expectedComment);

        Comment actualComment = commentDao.read(expectedComment.getId());

        Assert.assertNull(actualComment);
    }

    @Test
    public void testGetCommentsList() {
        List<Comment> expectedComments = new ArrayList<>();

        Comment expectedComment1 = new Comment();
        expectedComment1.setId(1);
        expectedComment1.setIdBook(1);
        expectedComment1.setIdReader(1);
        expectedComment1.setReview("review1");
        expectedComment1.setUser(commentDao.getLoginByReader(1));
        expectedComments.add(expectedComment1);

        Comment expectedComment2 = new Comment();
        expectedComment2.setId(6);
        expectedComment2.setIdBook(1);
        expectedComment2.setIdReader(1);
        expectedComment2.setReview("review6");
        expectedComment2.setUser(commentDao.getLoginByReader(1));
        expectedComments.add(expectedComment2);

        Comment expectedComment3 = new Comment();
        expectedComment3.setId(11);
        expectedComment3.setIdBook(1);
        expectedComment3.setIdReader(1);
        expectedComment3.setReview("review11");
        expectedComment3.setUser(commentDao.getLoginByReader(1));
        expectedComments.add(expectedComment3);

        Comment expectedComment4 = new Comment();
        expectedComment4.setId(16);
        expectedComment4.setIdBook(1);
        expectedComment4.setIdReader(1);
        expectedComment4.setReview("review16");
        expectedComment4.setUser(commentDao.getLoginByReader(1));
        expectedComments.add(expectedComment4);

        List<Comment> actualComments = commentDao.getCommentsByBookId(1); //вот тут у меня немного затуп

        Assert.assertEquals(actualComments, expectedComments);
    }


    @Test
    public void testGetLoginByReader() {
        String expectedLogin = "login1";
        String actualLogin = commentDao.getLoginByReader(1);
        Assert.assertEquals(actualLogin, expectedLogin);
    }
}
