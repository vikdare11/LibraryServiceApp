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

public class EditBookCommand implements Command{
    private static EditBookCommand ourInstance = new EditBookCommand();

    public static EditBookCommand getInstance() {
        return ourInstance;
    }

    private EditBookCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        BookDao bookDao = BookDaoImpl.getInstance();
        PathDao pathDao = PathDaoImpl.getInstance();

        int bookId = Integer.parseInt(request.getParameter("book_id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Book book = new Book();
        book.setId(bookId);
        book.setName(title);
        book.setDescription(description);
        if (!title.isEmpty() && !description.isEmpty()) {
            bookDao.update(book);
        }
        if (bookId != -1) {
            Path path = new Path();
            String readPath = null;
            try {
                readPath = uploadBook(request, "read_file");
            } catch (IOException | ServletException e) {
                return OpenEditBookCommand.getInstance().execute(request);
            }

            path.setIdBook(bookId);
            path.setPath(readPath);
            path.setFormat(readPath.split("\\.")[1]);
            path.setId(pathDao.getPathIdByFormatAndBookId(path.getFormat(), path.getIdBook()));
            if (!readPath.isEmpty()) {
                pathDao.update(path);
            }

            String fb2Path = null;
            try {
                fb2Path = uploadBook(request, "fb2_file");
            } catch (IOException | ServletException e) {
                return OpenEditBookCommand.getInstance().execute(request);
            }

            path.setIdBook(bookId);
            path.setPath(fb2Path);
            path.setFormat(fb2Path.split("\\.")[1]);
            path.setId(pathDao.getPathIdByFormatAndBookId(path.getFormat(), path.getIdBook()));

            if (!fb2Path.isEmpty()) {
                pathDao.update(path);
            }

            String pdfPath = null;
            try {
                pdfPath = uploadBook(request, "pdf_file");
            } catch (IOException | ServletException e) {
                return OpenEditBookCommand.getInstance().execute(request);
            }

            path.setIdBook(bookId);
            path.setPath(pdfPath);
            path.setFormat(pdfPath.split("\\.")[1]);
            path.setId(pathDao.getPathIdByFormatAndBookId(path.getFormat(), path.getIdBook()));

            if (!pdfPath.isEmpty()) {
                pathDao.update(path);
            }

            String txtPath = null;
            try {
                txtPath = uploadBook(request, "txt_file");
            } catch (IOException | ServletException e) {
                return OpenEditBookCommand.getInstance().execute(request);
            }

            path.setIdBook(bookId);
            path.setPath(txtPath);
            path.setFormat(txtPath.split("\\.")[1]);
            path.setId(pathDao.getPathIdByFormatAndBookId(path.getFormat(), path.getIdBook()));

            if (!txtPath.isEmpty()) {
                pathDao.update(path);
            }

        }
        return GetBooksCommand.getInstance().execute(request);
    }

    private String uploadBook(HttpServletRequest request, String format) throws IOException, ServletException {
        String bookPath = request.getServletContext().getRealPath("WEB-INF/Books/");
        try {
            if (!Files.exists(Paths.get(bookPath))) {
                Files.createDirectory(Paths.get(bookPath));
            }
        } catch (IOException e) {
            return OpenEditBookCommand.getInstance().execute(request);
        }

        Part part = request.getPart(format);

        String fileName = getSubmittedFileName(part);

        File file = new File(bookPath + File.separator + fileName);

        try {
            file.createNewFile();
        } catch (IOException e) {
            return OpenEditBookCommand.getInstance().execute(request);
        }

        try(
                InputStream inputStream = request.getPart(format).getInputStream();
                FileOutputStream outputStream = new FileOutputStream(file)
        )
        {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } catch (ServletException | IOException e) {
            return OpenEditBookCommand.getInstance().execute(request);
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
