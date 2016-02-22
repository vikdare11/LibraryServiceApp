package dao;

import domain.Reader;

import java.util.List;

/**
 * Created by Vika on 2/22/2016.
 */
public interface ReaderDao {
    int create(Reader reader);
    Reader read(int idReader);
    void update(Reader reader);
    void delete(Reader reader);
    List<Reader> getReadersList();
}
