package controller;

import controller.command.DocumentGenerationCommand;
import controller.command.implementation.documents.csv.*;
import controller.command.implementation.documents.pdf.*;
import controller.command.implementation.documents.xls.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@MultipartConfig
public class BookDownloadController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter("command");
        String path = "D://documents//";
        String docType = null;

        DocumentGenerationCommand command = null;
        if (commandName != null) {
            switch (commandName) {
                case "download_book":
                    String bookPath = req.getParameter("bookPath");
                    download(req, resp, bookPath);
                    break;

                case "generate_books_list":

                    docType = req.getParameter("doc_type");
                    switch (docType) {
                        case "csv_type":
                            command = CsvBooksListGenerateCommand.getInstance();
                            command.execute(req, resp);
                            break;
                        case "pdf_type":
                            try {
                                command = PdfBooksListGenerateCommand.getInstance();
                                command.execute(req, resp);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "xls_type":
                            command = XlsBooksListGenerateCommand.getInstance();
                            command.execute(req, resp);
                            break;
                    }
                    break;
                case "generate_users_list":
                    path += "UsersList";
                    docType = req.getParameter("doc_type");
                    switch (docType) {
                        case "csv_type":
                            command = CsvUsersListGenerateCommand.getInstance();
                            command.execute(req, resp);
                            break;
                        case "pdf_type":
                            command = PdfUsersListGenerateCommand.getInstance();
                            command.execute(req, resp);
                            break;
                        case "xls_type":
                            command = XlsUsersListGenerateCommand.getInstance();
                            command.execute(req, resp);
                            break;
                    }
                    break;
                case "generate_books_info":
                    path += "BooksInfo";
                    docType = req.getParameter("doc_type");
                    switch (docType) {
                        case "csv_type":
                            command = CsvBooksInfoGenerateCommand.getInstance();
                            command.execute(req, resp);
                            break;
                        case "pdf_type":
                            command = PdfBooksInfoGenerateCommand.getInstance();
                            command.execute(req, resp);
                            break;
                        case "xls_type":
                            command = XlsBooksInfoGenerateCommand.getInstance();
                            command.execute(req, resp);
                            break;
                    }
                    break;
                case "generate_views_statistic":
                    path += "ViewsStatistic";
                    docType = req.getParameter("doc_type");
                    switch (docType) {
                        case "csv_type":
                            command = CsvViewsStatisticGenerateCommand.getInstance();
                            command.execute(req, resp);
                            break;
                        case "pdf_type":
                            command = PdfViewsStatisticGenerateCommand.getInstance();
                            command.execute(req, resp);
                            break;
                        case "xls_type":
                            command = XlsViewsStatisticGenerateCommand.getInstance();
                            command.execute(req, resp);
                            break;
                    }
                    break;
                case "generate_book_collections":
                    path += "BooksCollectionsOfReader";
                    docType = req.getParameter("doc_type");
                    switch (docType) {
                        case "csv_type":
                            command = CsvBooksCollectionsOfReadersGenerateCommand.getInstance();
                            command.execute(req, resp);
                            break;
                        case "pdf_type":
                            command = PdfBooksCollectionsOfReadersGenerateCommand.getInstance();
                            command.execute(req, resp);
                            break;
                        case "xls_type":
                            command = XlsBooksCollectionsOfReadersGenerateCommand.getInstance();
                            command.execute(req, resp);
                            break;
                    }
                    break;
            }
        }
    }

    private void download(HttpServletRequest req, HttpServletResponse resp, String path) {

        String savingFileName = path.split("/")[1];

        path = "WEB-INF/" + path;

        resp.setContentType("text/plain");
        resp.setHeader("Content-Disposition",
                "attachment;filename=" + savingFileName);
        ServletContext ctx = getServletContext();

        InputStream is = ctx.getResourceAsStream(path);

        try {
            int read = 0;
            byte[] bytes = new byte[1024];
            OutputStream os = resp.getOutputStream();

            while ((read = is.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}