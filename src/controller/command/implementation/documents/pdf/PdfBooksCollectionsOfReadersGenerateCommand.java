package controller.command.implementation.documents.pdf;

import com.itextpdf.text.DocumentException;
import controller.command.DocumentGenerationCommand;
import document.implementation.PdfDocumentGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PdfBooksCollectionsOfReadersGenerateCommand implements DocumentGenerationCommand {
    private static PdfBooksCollectionsOfReadersGenerateCommand ourInstance = new PdfBooksCollectionsOfReadersGenerateCommand();

    public static PdfBooksCollectionsOfReadersGenerateCommand getInstance() {
        return ourInstance;
    }

    private PdfBooksCollectionsOfReadersGenerateCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PdfDocumentGenerator generator = (PdfDocumentGenerator) PdfDocumentGenerator.getInstance();
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + "booksCollection.pdf");
            generator.setIMAGE_BACK(request.getServletContext().getRealPath("/image/background.jpg"));
            generator.generateBookCollectionsOfReaders(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "index.jsp";
    }
}
