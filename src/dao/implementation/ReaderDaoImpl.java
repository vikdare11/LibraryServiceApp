package dao.implementation;

import dao.ReaderDao;
import dao.util.DbUtil;
import domain.Reader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderDaoImpl implements ReaderDao {

    private static final ReaderDao instance = new ReaderDaoImpl();

    private ReaderDaoImpl() {}

    public static ReaderDao getInstance() {
        return instance;
    }

    @Override
    public int create(Reader reader) {
        int id = 0;

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into `reader` (iduser, email) " +
                     "values (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, reader.getIdUser());
            statement.setString(2, reader.getEmail());
            statement.execute();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Reader read(int idReader) {
        Reader reader = null;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from `reader` where id=?")) {
            statement.setInt(1, idReader);

            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    reader = new Reader();
                    reader.setIdUser(resultSet.getInt("iduser"));
                    reader.setEmail(resultSet.getString("email"));
                    reader.setId(idReader);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reader;
    }

    @Override
    public void update(Reader reader) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("update `reader` set iduser=?, email=? "+
                     "where id=?")) {
            statement.setInt(1, reader.getIdUser());
            statement.setString(2, reader.getEmail());
            statement.setInt(3, reader.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Reader reader) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("delete from `reader` where id=?")){
            statement.setInt(1, reader.getId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
