package controller.command.implementation.book;

import controller.command.Command;
import dao.BookDao;
import dao.PathDao;
import dao.implementation.BookDaoImpl;
import dao.implementation.PathDaoImpl;
import domain.Book;
import domain.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AddBookCommand implements Command {
    private static final Command instance = new AddBookCommand();

    private AddBookCommand(){}

    public static Command getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        BookDao bookDao = BookDaoImpl.getInstance();

        int authorId = Integer.parseInt(request.getParameter("author_id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Book book = new Book();
        book.setId(authorId);
        book.setName(title);
        book.setDescription(description);
        int bookId = -1;
        if (!title.isEmpty() && !description.isEmpty()) {
            bookId = bookDao.create(book);
        }
        if (bookId != -1) {
            Path path = new Path();
            String downloadPath = null;
            try {
                downloadPath = uploadBook(request);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            }

            path.setIdBook(bookId);
            path.setPath(downloadPath);
            path.setFormat(downloadPath.split("\\.")[1]);
            PathDao pathDao = PathDaoImpl.getInstance();

            /*Path read = new Path();
            String readPath = request.getParameter("readPath");
            read.setPath(readPath);
            read.setFormat(readPath.split("\\.")[1]);*/

            if (!downloadPath.isEmpty()) {
                pathDao.create(path);
                //pathDao.create(read);
            }


            bookDao.insertBookAuthorLink(bookId, authorId);
        }
        return GetBooksCommand.getInstance().execute(request);
    }

    private String uploadBook(HttpServletRequest request) throws IOException, ServletException {
        String bookPath = request.getServletContext().getRealPath("WEB-INF/Books/");
        try {
            if (!Files.exists(Paths.get(bookPath))) {
                Files.createDirectory(Paths.get(bookPath));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Part part = request.getPart("file");

        String fileName = getSubmittedFileName(part);

        File file = new File(bookPath + File.separator + fileName);

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(
                InputStream inputStream = request.getPart("file").getInputStream();
                FileOutputStream outputStream = new FileOutputStream(file)
        )
        {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

        return "Books/"+ fileName;
    }

    private static String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
}
