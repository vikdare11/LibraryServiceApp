package controller;

import controller.command.Command;
import controller.command.implementation.documents.CsvDocumentsGenerateCommand;
import controller.command.implementation.documents.PdfDocumentsGenerateCommand;
import controller.command.implementation.documents.XlsDocumentsGenerateCommand;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@MultipartConfig
public class BookDownloadController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter("command");
        String path = null;
        List<String> paths = null;
        Command command = null;
        if (commandName != null) {
            switch (commandName) {
                case "download_book":
                    String bookPath = req.getParameter("bookPath");
                    download(req, resp, bookPath);
                    break;
                case "generate_pdf_documents" :
                    command = PdfDocumentsGenerateCommand.getInstance();
                    command.execute(req);
                    paths = (List<String>) req.getAttribute("paths");
                    path = "D://documents//pdf.zip";
                    archive(paths, path);
                    downloadDoc(req, resp, path);
                    break;
                case "generate_xls_documents" :
                    command = XlsDocumentsGenerateCommand.getInstance();
                    command.execute(req);
                    paths = (List<String>) req.getAttribute("paths");
                    path = "D://documents//xls.zip";
                    archive(paths, path);
                    downloadDoc(req, resp, path);
                    break;
                case "generate_csv_documents" :
                    command = CsvDocumentsGenerateCommand.getInstance();
                    command.execute(req);
                    paths = (List<String>) req.getAttribute("paths");
                    path = "D://documents//csv.zip";
                    archive(paths, path);
                    downloadDoc(req, resp, path);
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

    private void downloadDoc(HttpServletRequest req, HttpServletResponse resp, String path) {

        String savingFileName = path.split("//")[2];

        resp.setContentType("text/plain");
        resp.setHeader("Content-Disposition",
                "attachment;filename=" + savingFileName);
        ServletContext ctx = getServletContext();

        InputStream is = null;
        try {
            is = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            int read = 0;
            byte[] bytes = new byte[1024];
            OutputStream os = resp.getOutputStream();

            while ((read = is.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }
            os.flush();
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        resp.reset();
    }

    private void archive(List<String> paths, String outputFile) throws IOException {
        int level = 9;
        FileOutputStream fout = new FileOutputStream(outputFile);
        ZipOutputStream zout = new ZipOutputStream(fout);
        zout.setLevel(level);
        for (String path : paths) {
            ZipEntry ze = new ZipEntry(path.split("//")[2]);
            FileInputStream fin = new FileInputStream(path);
            try {
                zout.putNextEntry(ze);
                for (int c = fin.read(); c != -1; c = fin.read()) {
                    zout.write(c);
                }
            } finally {
                fin.close();
            }
        }
        zout.close();
    }
}
