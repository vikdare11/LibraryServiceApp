import domain.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Controller extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Book> books = new ArrayList<>();

        try(Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from book");
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("idbook"));
                book.setName(resultSet.getString("name"));
                book.setIdAuthor(resultSet.getInt("idauthor"));
                book.setDescription(resultSet.getString("description"));
                book.setCountOfViews(resultSet.getInt("countOfViews"));

                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("books", books);

        RequestDispatcher dispatcher = req.getRequestDispatcher("books.jsp");
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        }
    }

    private Connection getConnection() {
        String driverName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String pass = "";

        Connection connection = null;
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
