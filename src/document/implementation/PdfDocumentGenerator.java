package document.implementation;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.BookDao;
import dao.UserDao;
import dao.implementation.BookDaoImpl;
import dao.implementation.UserDaoImpl;
import document.IDocumentGenerator;
import domain.*;
import service.Service;
import service.implementation.GetBookInfoService;
import service.implementation.GetUserInfoService;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfDocumentGenerator implements IDocumentGenerator{

    private static final IDocumentGenerator instance = new PdfDocumentGenerator();

    private PdfDocumentGenerator(){}

    public static IDocumentGenerator getInstance() {
        return instance;
    }

    private Document booksListDocument;
    private Document usersListDocument;
    private Document booksInfoDocument;
    private BookDao bookDao = BookDaoImpl.getInstance();
    private UserDao userDao = UserDaoImpl.getInstance();
    Service<Integer, UserViewObject> getUserInfoService = GetUserInfoService.getInstance();
    private List<Book> booksList;
    private List<UserViewObject> readersList;
    private List<BookViewObject> booksInfoList;
    private static String USER_PASS = "";
    private static String OWNER_PASS = "owner-pass";

    @Override
    public void generateBooksList(String outputFile) throws IOException, DocumentException {
        readersList = new ArrayList<>();
        booksInfoList = new ArrayList<>();
        booksListDocument = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(booksListDocument, new FileOutputStream(outputFile));
        writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
        booksListDocument.open();
        booksList = bookDao.getBooksList();
        Paragraph title = new Paragraph("Books list: ", FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new CMYKColor(0, 255, 255,17)));
        Chapter chapter = new Chapter(title, 1);
        chapter.setNumberDepth(0);
        Section section = chapter.addSection(title);
        PdfPTable table = new PdfPTable(3);
        table.setSpacingBefore(25);
        table.setSpacingAfter(25);
        table.addCell(new PdfPCell(new Phrase("Author", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)))));
        table.addCell(new PdfPCell(new Phrase("Title", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)))));
        table.addCell(new PdfPCell(new Phrase("Description", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)))));
        for (Book book : booksList) {
            table.addCell(book.getAuthor());
            table.addCell(book.getName());
            table.addCell(book.getDescription());
        }
        section.add(table);
        booksListDocument.add(section);
        booksListDocument.close();
    }

    @Override
    public void generateUsersList(String outputFile) throws FileNotFoundException, DocumentException {
        readersList = new ArrayList<>();
        booksInfoList = new ArrayList<>();
        usersListDocument = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(usersListDocument, new FileOutputStream(outputFile));
        writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
        usersListDocument.open();
        List<User> usersList = userDao.getUsersList();
        for (User user : usersList) {
            readersList.add(getUserInfoService.execute(user.getId()));
        }
        Paragraph title = new Paragraph("Users list: ",
                FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new CMYKColor(0, 255, 255,17)));

        Chapter chapter = new Chapter(title, 1);
        chapter.setNumberDepth(0);
        Section section = chapter.addSection(title);
        PdfPTable table = new PdfPTable(3);
        table.setSpacingBefore(25);
        table.setSpacingAfter(25);
        table.addCell(new PdfPCell(new Phrase("Login", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)))));
        table.addCell(new PdfPCell(new Phrase("E-mail", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)))));
        table.addCell(new PdfPCell(new Phrase("Is user admin", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)))));
        for (UserViewObject reader : readersList) {
            table.addCell(reader.getLogin());
            table.addCell(reader.getEmail());
            table.addCell(reader.isAdmin() ? "Admin" : "Not admin");
        }
        section.add(table);
        usersListDocument.add(section);
        usersListDocument.close();

    }

    @Override
    public void generateBooksInfo(String outputFile) throws FileNotFoundException, DocumentException {
        readersList = new ArrayList<>();
        booksInfoList = new ArrayList<>();
        booksInfoDocument = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(booksInfoDocument, new FileOutputStream(outputFile));
        writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
        booksInfoDocument.open();
        booksList = bookDao.getBooksList();
        Service<Integer, BookViewObject> getBookInfoService = GetBookInfoService.getInstance();
        for (Book book : booksList) {
            booksInfoList.add(getBookInfoService.execute(book.getId()));
        }
        Paragraph title = new Paragraph("Books list with full information: ",
                FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new CMYKColor(0, 255, 255,17)));

        Chapter chapter = new Chapter(title, 1);
        chapter.setNumberDepth(0);
        Section section = chapter.addSection(title);
        for (BookViewObject book : booksInfoList) {
            PdfPTable table = new PdfPTable(3);
            table.setSpacingBefore(25);
            table.setSpacingAfter(25);
            table.addCell(new PdfPCell(new Phrase("Author", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)))));
            table.addCell(new PdfPCell(new Phrase("Title", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)))));
            table.addCell(new PdfPCell(new Phrase("Description", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)))));

            table.addCell(book.getAuthor().getName() + " " + book.getAuthor().getSurname());
            table.addCell(book.getBook().getName());
            table.addCell(book.getBook().getDescription());

            section.add(table);
            section.add(new Paragraph("Comments: ", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17))));
            for (Comment comment: book.getListOfComments()) {
                section.add(new Paragraph(comment.getUser() + ": " + comment.getReview()));
            }

            section.add(new Paragraph("Available paths: ", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17))));
            String formats = book.getReadPath().getFormat();
            for (Path path : book.getDownloadPaths()) {
                formats += ", " + path.getFormat();
            }
            section.add(new Paragraph(formats + "\n\n\n\n\n"));
        }
        booksInfoDocument.add(section);
        booksInfoDocument.close();
    }

    @Override
    public void generateViewsStatistic(String outputFile) throws FileNotFoundException, DocumentException {
        readersList = new ArrayList<>();
        booksInfoList = new ArrayList<>();
        booksListDocument = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(booksListDocument, new FileOutputStream(outputFile));
        writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
        booksListDocument.open();
        booksList = bookDao.getBooksList();
        Paragraph title = new Paragraph("Books list: ",
                FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new CMYKColor(0, 255, 255,17)));

        Chapter chapter = new Chapter(title, 1);
        chapter.setNumberDepth(0);
        Section section = chapter.addSection(title);
        PdfPTable table = new PdfPTable(3);
        table.setSpacingBefore(25);
        table.setSpacingAfter(25);
        table.addCell(new PdfPCell(new Phrase("Author", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)))));
        table.addCell(new PdfPCell(new Phrase("Title", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)))));
        table.addCell(new PdfPCell(new Phrase("Count of views", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)))));
        for (Book book : booksList) {
            table.addCell(book.getAuthor());
            table.addCell(book.getName());
            table.addCell(String.valueOf(book.getCountOfViews()));
        }
        section.add(table);
        booksListDocument.add(section);
        booksListDocument.close();
    }

    @Override
    public void generateBookCollectionsOfReaders(String outputFile) throws FileNotFoundException, DocumentException {
        readersList = new ArrayList<>();
        booksInfoList = new ArrayList<>();
        usersListDocument = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(usersListDocument, new FileOutputStream(outputFile));
        writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
        usersListDocument.open();
        Paragraph title = new Paragraph("Users list: ",
                FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new CMYKColor(0, 255, 255,17)));

        Chapter chapter = new Chapter(title, 1);
        chapter.setNumberDepth(0);
        Section section = chapter.addSection(title);
        List<User> usersList = userDao.getUsersList();
        for (User user : usersList) {
            readersList.add(getUserInfoService.execute(user.getId()));
        }
        for (UserViewObject reader : readersList) {
            section.add(new Paragraph(reader.getLogin(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17))));
            PdfPTable table = new PdfPTable(3);
            table.setSpacingBefore(25);
            table.setSpacingAfter(25);
            table.addCell(new PdfPCell(new Phrase("Author", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)))));
            table.addCell(new PdfPCell(new Phrase("Title", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)))));
            table.addCell(new PdfPCell(new Phrase("Description", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)))));
            for (Book book : reader.getBookCollection()) {
                table.addCell(book.getAuthor());
                table.addCell(book.getName());
                table.addCell(book.getDescription());
            }
            section.add(table);
        }
        usersListDocument.add(section);
        usersListDocument.close();
    }
}
