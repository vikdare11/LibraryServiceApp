package document.implementation;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import dao.BookDao;
import dao.UserDao;
import dao.implementation.BookDaoImpl;
import dao.implementation.UserDaoImpl;
import document.IDocumentGenerator;
import domain.*;
import service.Service;
import service.implementation.GetBookInfoService;
import service.implementation.GetUserInfoService;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class PdfDocumentGenerator implements IDocumentGenerator {

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

    private String IMAGE_BACK;

    public void setIMAGE_BACK(String IMAGE_BACK) {
        this.IMAGE_BACK = IMAGE_BACK;
    }

    @Override
    public void generateBooksList(HttpServletResponse response) throws IOException, DocumentException {
        readersList = new ArrayList<>();
        booksInfoList = new ArrayList<>();
        booksListDocument = new Document(PageSize.A4, 50, 50, 50, 50);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter writer = PdfWriter.getInstance(booksListDocument, baos);

        writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
        MyEvent event = new MyEvent();
        writer.setPageEvent(event);
        booksListDocument.open();
        booksList = bookDao.getBooksList();
        Paragraph title = new Paragraph("Books list: ", FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new CMYKColor(0, 255, 255,17)));
        PdfPTable table = new PdfPTable(3);
        table.setSpacingBefore(25);
        table.setSpacingAfter(25);
        table.addCell(new PdfPCell(new Phrase("Author", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)))));
        table.addCell(new PdfPCell(new Phrase("Title", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)))));
        table.addCell(new PdfPCell(new Phrase("Description", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255, 17)))));
        for (Book book : booksList) {
            table.addCell(book.getAuthor());
            table.addCell(book.getName());
            table.addCell(book.getDescription());
        }
        booksListDocument.add(title);
        booksListDocument.add(table);
        booksListDocument.close();

        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        // setting the content type
        response.setContentType("application/pdf");
        // the contentlength
        response.setContentLength(baos.size());
        // write ByteArrayOutputStream to the ServletOutputStream
        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
        os.close();

    }

    @Override
    public void generateUsersList(HttpServletResponse response) throws IOException, DocumentException {
        readersList = new ArrayList<>();
        booksInfoList = new ArrayList<>();
        usersListDocument = new Document(PageSize.A4, 50, 50, 50, 50);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter writer = PdfWriter.getInstance(usersListDocument, baos);

        writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
        MyEvent event = new MyEvent();
        writer.setPageEvent(event);
        usersListDocument.open();
        List<User> usersList = userDao.getUsersList();
        for (User user : usersList) {
            readersList.add(getUserInfoService.execute(user.getId()));
        }
        Paragraph title = new Paragraph("Users list: ",
                FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new CMYKColor(0, 255, 255,17)));

        PdfPTable table = new PdfPTable(3);
        table.setSpacingBefore(25);
        table.setSpacingAfter(25);
        table.addCell(new PdfPCell(new Phrase("Login", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)))));
        table.addCell(new PdfPCell(new Phrase("E-mail", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)))));
        table.addCell(new PdfPCell(new Phrase("Is client admin", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)))));
        for (UserViewObject reader : readersList) {
            table.addCell(reader.getLogin());
            table.addCell(reader.getEmail());
            table.addCell(reader.isAdmin() ? "Admin" : "Not admin");
        }
        usersListDocument.add(title);
        usersListDocument.add(table);
        usersListDocument.close();
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        // setting the content type
        response.setContentType("application/pdf");
        // the contentlength
        response.setContentLength(baos.size());
        // write ByteArrayOutputStream to the ServletOutputStream
        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
        os.close();
    }

    @Override
    public void generateBooksInfo(HttpServletResponse response) throws IOException, DocumentException {
        readersList = new ArrayList<>();
        booksInfoList = new ArrayList<>();
        booksInfoDocument = new Document(PageSize.A4, 50, 50, 50, 50);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter writer = PdfWriter.getInstance(booksInfoDocument, baos);

        writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
        MyEvent event = new MyEvent();
        writer.setPageEvent(event);
        booksInfoDocument.open();
        booksList = bookDao.getBooksList();
        Service<Integer, BookViewObject> getBookInfoService = GetBookInfoService.getInstance();
        for (Book book : booksList) {
            booksInfoList.add(getBookInfoService.execute(book.getId()));
        }
        Paragraph title = new Paragraph("Books list with full information: \n\n",
                FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new CMYKColor(0, 255, 255,17)));

        booksInfoDocument.add(title);
        Chapter chapter = new Chapter(title, 1);

        for (BookViewObject book : booksInfoList) {
            chapter.setNumberDepth(0);
            Paragraph bookParagraph = new Paragraph(book.getAuthor().getName() + " " + book.getAuthor().getSurname() +
                    " - " + book.getBook().getName(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)));
            Section section = chapter.addSection(bookParagraph);
            section.add(new Paragraph("Description: ", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17))));
            section.add(new Paragraph(book.getBook().getDescription()));
            section.add(new Paragraph("Comments: ", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17))));
            if (book.getListOfComments().isEmpty()) {
                section.add(new Paragraph("No comment."));
            }
            for (Comment comment: book.getListOfComments()) {
                section.add(new Paragraph(comment.getUser() + ": " + comment.getReview()));
            }

            section.add(new Paragraph("Available paths: ", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17))));
            String formats = book.getReadPath().getFormat();
            for (Path path : book.getDownloadPaths()) {
                formats += ", " + path.getFormat();
            }
            section.add(new Paragraph(formats + "\n\n"));
            booksInfoDocument.add(section);
        }
        booksInfoDocument.close();

        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        // setting the content type
        response.setContentType("application/pdf");
        // the contentlength
        response.setContentLength(baos.size());
        // write ByteArrayOutputStream to the ServletOutputStream
        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
        os.close();
    }

    @Override
    public void generateViewsStatistic(HttpServletResponse response) throws IOException, DocumentException {
        readersList = new ArrayList<>();
        booksInfoList = new ArrayList<>();
        booksListDocument = new Document(PageSize.A4, 50, 50, 50, 50);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter writer = PdfWriter.getInstance(booksListDocument, baos);

        writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
        MyEvent event = new MyEvent();
        writer.setPageEvent(event);
        booksListDocument.open();
        booksList = bookDao.getBooksList();
        Paragraph title = new Paragraph("Book views statistic: ",
                FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new CMYKColor(0, 255, 255,17)));

        PdfPTable table = new PdfPTable(3);
        table.setSpacingBefore(25);
        table.setSpacingAfter(25);
        table.addCell(new PdfPCell(new Phrase("Author", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)))));
        table.addCell(new PdfPCell(new Phrase("Title", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255,17)))));
        table.addCell(new PdfPCell(new Phrase("Count of views", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(0, 255, 255, 17)))));
        for (Book book : booksList) {
            table.addCell(book.getAuthor());
            table.addCell(book.getName());
            table.addCell(String.valueOf(book.getCountOfViews()));
        }
        booksListDocument.add(title);
        booksListDocument.add(table);
        booksListDocument.close();

        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        // setting the content type
        response.setContentType("application/pdf");
        // the contentlength
        response.setContentLength(baos.size());
        // write ByteArrayOutputStream to the ServletOutputStream
        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
        os.close();

    }

    @Override
    public void generateBookCollectionsOfReaders(HttpServletResponse response) throws IOException, DocumentException {
        readersList = new ArrayList<>();
        booksInfoList = new ArrayList<>();
        usersListDocument = new Document(PageSize.A4, 50, 50, 50, 50);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(usersListDocument, baos);
        writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
        MyEvent event = new MyEvent();
        writer.setPageEvent(event);
        usersListDocument.open();

        List<User> usersList = userDao.getUsersList();
        for (User user : usersList) {
            readersList.add(getUserInfoService.execute(user.getId()));
        }
        int i = 0;
        for (UserViewObject reader : readersList) {
            i++;
            Paragraph userTitle = new Paragraph(reader.getLogin(),
                    FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new CMYKColor(0, 255, 255,17)));
            Chapter chapter = new Chapter(userTitle, i);
            chapter.setNumberDepth(0);
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
            chapter.add(table);
            usersListDocument.add(chapter);
        }
        usersListDocument.close();
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        // setting the content type
        response.setContentType("application/pdf");
        // the contentlength
        response.setContentLength(baos.size());
        // write ByteArrayOutputStream to the ServletOutputStream
        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
        os.close();
    }

    protected class MyEvent extends PdfPageEventHelper {
        Image image = null;

        @Override
        public void onOpenDocument(PdfWriter writer, Document document) {
            try {
                image = Image.getInstance(IMAGE_BACK);
            } catch (BadElementException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.setAbsolutePosition(12, 300);
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                PdfContentByte canvas = writer.getDirectContentUnder();
                if (image != null) {
                    image.scaleAbsolute(PageSize.A4.getWidth(), PageSize.A4.getHeight());
                    image.setAbsolutePosition(0, 0);
                    canvas.saveState();
                    PdfGState pdfGState = new PdfGState();
                    pdfGState.setFillOpacity(0.8f);
                    canvas.setGState(pdfGState);
                    canvas.addImage(image);
                    canvas.restoreState();
                }
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
    }
}



