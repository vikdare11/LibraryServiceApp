package controller.command.implementation.documents;

import com.itextpdf.text.DocumentException;
import controller.command.Command;
import document.IDocumentGenerator;
import document.implementation.PdfDocumentGenerator;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PdfDocumentsGenerateCommand implements Command {
    private static PdfDocumentsGenerateCommand ourInstance = new PdfDocumentsGenerateCommand();

    public static PdfDocumentsGenerateCommand getInstance() {
        return ourInstance;
    }

    private PdfDocumentsGenerateCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        IDocumentGenerator generator = PdfDocumentGenerator.getInstance();
        try {
            generator.generateBooksList("D://documents//booksListDocument.pdf");
            generator.generateUsersList("D://documents//usersListDocument.pdf");
            generator.generateBooksInfo("D://documents//booksInfoDocument.pdf");
            generator.generateViewsStatistic("D://documents//booksViewStatistic.pdf");
            generator.generateBookCollectionsOfReaders("D://documents//usersBookCollections.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "index.jsp";
    }
}
