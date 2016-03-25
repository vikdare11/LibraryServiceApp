package dao;

import domain.Book;

import java.util.List;

public interface BookDao {
    int create(Book book);
    Book read(int idBook);
    void update(Book book);
    void delete(Book book);
    List<Book> getBooksList();
    void insertBookAuthorLink(Integer bookId, Integer authorId);
    void addBookToReaderCollection(int bookId, int readerId);
    boolean readerHasBook(int idReader, int idBook);
    void removeBookFromReaderCollection(int idReader, int idBook);
}
