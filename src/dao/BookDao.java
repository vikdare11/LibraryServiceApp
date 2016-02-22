package dao;

import domain.Book;

import java.util.List;

/**
 * Created by Vika on 2/22/2016.
 */
public interface BookDao {
    int create(Book book);
    Book read(int idBook);
    void update(Book book);
    void delete(Book book);
    List<Book> getBooksList();
}
