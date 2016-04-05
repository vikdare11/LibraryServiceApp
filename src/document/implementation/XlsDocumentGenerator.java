package document.implementation;

import com.itextpdf.text.DocumentException;
import dao.BookDao;
import dao.UserDao;
import dao.implementation.BookDaoImpl;
import dao.implementation.UserDaoImpl;
import document.IDocumentGenerator;
import domain.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import service.Service;
import service.implementation.GetBookInfoService;
import service.implementation.GetUserInfoService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XlsDocumentGenerator implements IDocumentGenerator {

    private static final IDocumentGenerator instance = new XlsDocumentGenerator();

    private XlsDocumentGenerator(){}

    public static IDocumentGenerator getInstance() {
        return instance;
    }

    private BookDao bookDao = BookDaoImpl.getInstance();
    private UserDao userDao = UserDaoImpl.getInstance();
    Service<Integer, UserViewObject> getUserInfoService = GetUserInfoService.getInstance();
    private List<Book> booksList;
    private List<UserViewObject> readersList = new ArrayList<>();
    private List<BookViewObject> booksInfoList = new ArrayList<>();

    @Override
    public void generateBooksList(String outputFile) throws IOException, DocumentException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Books list");
        int i = 0;
        Row row = sheet.createRow(i);

        Cell author = row.createCell(0);
        author.setCellValue("Author");
        Cell title = row.createCell(1);
        title.setCellValue("Title");
        Cell description = row.createCell(2);
        description.setCellValue("Description");

        booksList = bookDao.getBooksList();
        for (Book book : booksList) {
            i++;
            Row tempRow = sheet.createRow(i);
            tempRow.createCell(0).setCellValue(book.getAuthor());
            tempRow.createCell(1).setCellValue(book.getName());
            tempRow.createCell(2).setCellValue(book.getDescription());
        }
        sheet.autoSizeColumn(1);
        workbook.write(new FileOutputStream(outputFile));
        //book.close();
    }

    @Override
    public void generateUsersList(String outputFile) throws IOException, DocumentException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users list");
        int i = 0;
        Row row = sheet.createRow(i);

        Cell login = row.createCell(0);
        login.setCellValue("Login");
        Cell email = row.createCell(1);
        email.setCellValue("Email");
        Cell isAdmin = row.createCell(2);
        isAdmin.setCellValue("Is user admin");

        List<User> usersList = userDao.getUsersList();
        for (User user : usersList) {
            readersList.add(getUserInfoService.execute(user.getId()));
        }

        for (UserViewObject reader : readersList) {
            i++;
            Row tempRow = sheet.createRow(i);
            tempRow.createCell(0).setCellValue(reader.getLogin());
            tempRow.createCell(1).setCellValue(reader.getEmail());
            tempRow.createCell(2).setCellValue(reader.isAdmin() ? "Admin" : "Not admin");
        }
        sheet.autoSizeColumn(1);
        workbook.write(new FileOutputStream(outputFile));
    }

    @Override
    public void generateBooksInfo(String outputFile) throws IOException, DocumentException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Books list with full information");
        int i = 0;
        Row row = sheet.createRow(i);

        Cell author = row.createCell(0);
        author.setCellValue("Author");
        Cell title = row.createCell(1);
        title.setCellValue("Title");
        Cell description = row.createCell(2);
        description.setCellValue("Description");
        Cell paths = row.createCell(3);
        paths.setCellValue("Available paths");
        Cell comments = row.createCell(4);
        comments.setCellValue("Comments");

        booksList = bookDao.getBooksList();
        Service<Integer, BookViewObject> getBookInfoService = GetBookInfoService.getInstance();
        for (Book book : booksList) {
            booksInfoList.add(getBookInfoService.execute(book.getId()));
        }

        for (BookViewObject book : booksInfoList) {
            i++;
            Row tempRow = sheet.createRow(i);
            tempRow.createCell(0).setCellValue(book.getAuthor().getName() + " " + book.getAuthor().getSurname());
            tempRow.createCell(1).setCellValue(book.getBook().getName());
            tempRow.createCell(2).setCellValue(book.getBook().getDescription());
            String formats = book.getReadPath().getFormat();
            for (Path path : book.getDownloadPaths()) {
                formats += ", " + path.getFormat();
            }
            tempRow.createCell(3).setCellValue(formats);
            int k = 3;
            for (Comment comment: book.getListOfComments()) {
                k++;
                tempRow.createCell(k).setCellValue(comment.getUser() + ": " + comment.getReview());
            }
        }
        workbook.write(new FileOutputStream(outputFile));
    }

    @Override
    public void generateViewsStatistic(String outputFile) throws IOException, DocumentException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Books list");
        int i = 0;
        Row row = sheet.createRow(i);

        Cell author = row.createCell(0);
        author.setCellValue("Author");
        Cell title = row.createCell(1);
        title.setCellValue("Title");
        Cell countOfViews = row.createCell(2);
        countOfViews.setCellValue("Count of views");

        booksList = bookDao.getBooksList();
        for (Book book : booksList) {
            i++;
            Row tempRow = sheet.createRow(i);
            tempRow.createCell(0).setCellValue(book.getAuthor());
            tempRow.createCell(1).setCellValue(book.getName());
            tempRow.createCell(2).setCellValue(book.getCountOfViews());
        }
        sheet.autoSizeColumn(1);
        workbook.write(new FileOutputStream(outputFile));
    }

    @Override
    public void generateBookCollectionsOfReaders(String outputFile) throws IOException, DocumentException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Books list");
        for (UserViewObject reader : readersList) {
            int i = 0;
            Row row = sheet.createRow(i);
            Cell login = row.createCell(0);
            login.setCellValue(reader.getLogin());

            for (Book book : reader.getBookCollection()) {
                i++;
                Row tempRow = sheet.createRow(i);
                Cell author = tempRow.createCell(0);
                author.setCellValue("Author");
                Cell title = tempRow.createCell(1);
                title.setCellValue("Title");
                Cell description = tempRow.createCell(2);
                description.setCellValue("Description");
                i++;
                tempRow = sheet.createRow(i);
                tempRow.createCell(0).setCellValue(book.getAuthor());
                tempRow.createCell(1).setCellValue(book.getName());
                tempRow.createCell(2).setCellValue(book.getDescription());
            }
        }
        sheet.autoSizeColumn(1);
        workbook.write(new FileOutputStream(outputFile));
    }
}
