package service.implementation;

import dao.AuthorDao;
import dao.BookDao;
import dao.CommentDao;
import dao.implementation.AuthorDaoImpl;
import dao.implementation.BookDaoImpl;
import dao.implementation.CommentDaoImpl;
import domain.Author;
import domain.Book;
import domain.BookViewObject;
import domain.Comment;
import service.Service;

import java.util.List;

public class GetBookInfoService implements Service<Integer, BookViewObject> {

    private static final GetBookInfoService instance = new GetBookInfoService();

    private GetBookInfoService(){}

    public static GetBookInfoService getInstance() {
        return instance;
    }

    @Override
    public BookViewObject execute(Integer bookId) {
        BookDao bookDao = BookDaoImpl.getInstance();
        AuthorDao authorDao = AuthorDaoImpl.getInstance();
        CommentDao commentDao = CommentDaoImpl.getInstance();

        Book book = bookDao.read(bookId);
        Author author = authorDao.getAuthorByBook(book);
        List<Comment> listOfComments = commentDao.getCommentsByBookId(bookId);

        BookViewObject bookViewObject = new BookViewObject();
        bookViewObject.setAuthor(author);
        bookViewObject.setBook(book);
        bookViewObject.setListOfComments(listOfComments);

        return bookViewObject;
    }

}
