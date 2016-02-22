package dao.implementation;

import dao.ReaderDao;
import dao.util.DbUtil;
import domain.Reader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vika on 2/22/2016.
 */
public class ReaderDaoImpl implements ReaderDao {
    @Override
    public int create(Reader reader) {
        return 0;
    }

    @Override
    public Reader read(int idReader) {
        return null;
    }

    @Override
    public void update(Reader reader) {

    }

    @Override
    public void delete(Reader reader) {

    }

    @Override
    public List<Reader> getReadersList() {
        List<Reader> readers = new ArrayList<>();

        try(Connection connection = DbUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from reader");
            while (resultSet.next()) {
                Reader reader = new Reader();
                reader.setId(resultSet.getInt("idreader"));
                reader.setIdUser(resultSet.getInt("iduser"));
                reader.setEmail(resultSet.getString("email"));
                readers.add(reader);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return readers;
    }
}
