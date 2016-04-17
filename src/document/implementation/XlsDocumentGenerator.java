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
    private List<UserViewObject> readersList;
    private List<BookViewObject> booksInfoList;

    @Override
    public void generateBooksList(String outputFile) throws IOException, DocumentException {
        readersList = new ArrayList<>();
        booksInfoList = new ArrayList<>();
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Books list");
        int i = 0;
        Row row = sheet.createRow(i);

        Cell author = row.createCell(0);
        author.setCellValue("Author");
        Cell title = row.createCell(1);
        title.setCellValue("Title");

        booksList = bookDao.getBooksList();
        for (Book book : booksList) {
            i++;
            Row tempRow = sheet.createRow(i);
            tempRow.createCell(0).setCellValue(book.getAuthor());
            tempRow.createCell(1).setCellValue(book.getName());
        }
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        FileOutputStream fos = new FileOutputStream(outputFile);
        workbook.write(fos);
        fos.close();
    }

    @Override
    public void generateUsersList(String outputFile) throws IOException, DocumentException {
        readersList = new ArrayList<>();
        booksInfoList = new ArrayList<>();
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
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        FileOutputStream fos = new FileOutputStream(outputFile);
        workbook.write(fos);
        fos.close();
    }

    @Override
    public void generateBooksInfo(String outputFile) throws IOException, DocumentException {
        readersList = new ArrayList<>();
        booksInfoList = new ArrayList<>();
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Books list with full information");
        booksList = bookDao.getBooksList();
        Service<Integer, BookViewObject> getBookInfoService = GetBookInfoService.getInstance();
        for (Book book : booksList) {
            booksInfoList.add(getBookInfoService.execute(book.getId()));
        }

        int i = 0;
        for (BookViewObject book : booksInfoList) {
            Row row = sheet.createRow(i);
            Cell author = row.createCell(0);
            author.setCellValue("Author");
            Cell title = row.createCell(1);
            title.setCellValue("Title");
            Cell formatsCell = row.createCell(2);
            formatsCell.setCellValue("Available paths");
            i++;
            Row tempRow = sheet.createRow(i);
            tempRow.createCell(0).setCellValue(book.getAuthor().getName() + " " + book.getAuthor().getSurname());
            tempRow.createCell(1).setCellValue(book.getBook().getName());
            String formats = book.getReadPath().getFormat();
            for (Path path : book.getDownloadPaths()) {
                formats += ", " + path.getFormat();
            }
            tempRow.createCell(2).setCellValue(formats);
            i++;
            row = sheet.createRow(i);
            Cell description = row.createCell(0);
            description.setCellValue("Description");
            i++;
            tempRow = sheet.createRow(i);
            if (book.getBook().getDescription().toCharArray().length > 135) {
                int k  = 125;
                while ((book.getBook().getDescription().toCharArray()[k] != " ".charAt(0))) {
                    k++;
                }
                tempRow.createCell(0).setCellValue(book.getBook().getDescription().substring(0, k+1));
                i++;
                tempRow = sheet.createRow(i);
                tempRow.createCell(0).setCellValue(book.getBook().getDescription().substring(k+1));
                i++;

            } else {
                tempRow.createCell(0).setCellValue(book.getBook().getDescription());
                i++;
            }
            row = sheet.createRow(i);
            Cell comments = row.createCell(0);
            comments.setCellValue("Comments");
            if (book.getListOfComments().isEmpty()) {
                row.createCell(1).setCellValue("No comment.");
            }
            for (Comment comment: book.getListOfComments()) {
                i++;
                tempRow = sheet.createRow(i);
                int k = 0;
                if (comment.getReview().toCharArray().length > 135) {
                    int j  = 125;
                    while ((comment.getReview().toCharArray()[j] != " ".charAt(0))) {
                        j++;
                    }
                    tempRow.createCell(0).setCellValue(comment.getUser() + ": " + comment.getReview().substring(0, j+1));
                    i++;
                    tempRow = sheet.createRow(i);
                    tempRow.createCell(0).setCellValue(comment.getReview().substring(j+1));
                    i++;
                } else {
                    tempRow.createCell(k).setCellValue(comment.getUser() + ": " + comment.getReview());
                    k++;
                }
            }
            i += 3;
        }
        sheet.setColumnWidth(0, 64);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        FileOutputStream fos = new FileOutputStream(outputFile);
        workbook.write(fos);
        fos.close();
    }

    @Override
    public void generateViewsStatistic(String outputFile) throws IOException, DocumentException {
        readersList = new ArrayList<>();
        booksInfoList = new ArrayList<>();
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
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        FileOutputStream fos = new FileOutputStream(outputFile);
        workbook.write(fos);
        fos.close();
    }

    @Override
    public void generateBookCollectionsOfReaders(String outputFile) throws IOException, DocumentException {
        readersList = new ArrayList<>();
        booksInfoList = new ArrayList<>();
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Books list");
        int i = 0;
        List<User> usersList = userDao.getUsersList();
        for (User user : usersList) {
            readersList.add(getUserInfoService.execute(user.getId()));
        }
        for (UserViewObject reader : readersList) {
            Row row = sheet.createRow(i);
            Cell login = row.createCell(0);
            login.setCellValue(reader.getLogin());
            i++;
            Row tempRow = sheet.createRow(i);
            Cell author = tempRow.createCell(0);
            author.setCellValue("Author");
            Cell title = tempRow.createCell(1);
            title.setCellValue("Title");
            for (Book book : reader.getBookCollection()) {
                i++;
                tempRow = sheet.createRow(i);
                tempRow.createCell(0).setCellValue(book.getAuthor());
                tempRow.createCell(1).setCellValue(book.getName());
            }
            i += 2;
        }
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        FileOutputStream fos = new FileOutputStream(outputFile);
        workbook.write(fos);
        fos.close();
    }
}
