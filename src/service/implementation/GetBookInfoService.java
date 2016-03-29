package service.implementation;

import dao.AuthorDao;
import dao.BookDao;
import dao.CommentDao;
import dao.PathDao;
import dao.implementation.AuthorDaoImpl;
import dao.implementation.BookDaoImpl;
import dao.implementation.CommentDaoImpl;
import dao.implementation.PathDaoImpl;
import domain.*;
import service.Service;

import java.util.ArrayList;
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
        PathDao pathDao = PathDaoImpl.getInstance();

        Book book = bookDao.read(bookId);
        Author author = authorDao.getAuthorByBook(book);
        List<Comment> listOfComments = commentDao.getCommentsByBookId(bookId);
        Path readPath = pathDao.getPathsList("html", book);
        List<Path> downloadPaths = new ArrayList<Path>();
        downloadPaths.add(pathDao.getPathsList("fb2", book));
        downloadPaths.add(pathDao.getPathsList("pdf", book));
        downloadPaths.add(pathDao.getPathsList("txt", book));


        BookViewObject bookViewObject = new BookViewObject();
        bookViewObject.setAuthor(author);
        bookViewObject.setBook(book);
        bookViewObject.setListOfComments(listOfComments);
        bookViewObject.setReadPath(readPath);
        bookViewObject.setDownloadPaths(downloadPaths);
        book.setCountOfViews(book.getCountOfViews() + 1);
        bookDao.update(book);

        return bookViewObject;
    }

}
