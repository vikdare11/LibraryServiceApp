package controller.command.implementation.documents.pdf;

import com.itextpdf.text.DocumentException;
import controller.command.DocumentGenerationCommand;
import document.implementation.PdfDocumentGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PdfBooksInfoGenerateCommand implements DocumentGenerationCommand {
    private static PdfBooksInfoGenerateCommand ourInstance = new PdfBooksInfoGenerateCommand();

    public static PdfBooksInfoGenerateCommand getInstance() {
        return ourInstance;
    }

    private PdfBooksInfoGenerateCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PdfDocumentGenerator generator = (PdfDocumentGenerator) PdfDocumentGenerator.getInstance();
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + "booksInfo.pdf");
            generator.setIMAGE_BACK(request.getServletContext().getRealPath("/image/background.jpg"));
            generator.generateBooksInfo(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "index.jsp";
    }
}
