package dao;

import domain.Book;
import domain.Reader;

import java.util.List;

public interface ReaderDao {
    int create(Reader reader);
    Reader read(int idReader);
    void update(Reader reader);
    void delete(Reader reader);
    List<Reader> getReadersList();
    List<Book> getBookCollection(int idReader);
    int getReaderIdByUserId(int userId);
    boolean isEmailExist(String email);
}
