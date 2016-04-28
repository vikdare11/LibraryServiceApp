package controller.command.implementation.documents.csv;

import com.itextpdf.text.DocumentException;
import controller.command.DocumentGenerationCommand;
import document.IDocumentGenerator;
import document.implementation.CsvDocumentGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CsvBooksCollectionsOfReadersGenerateCommand implements DocumentGenerationCommand {
    private static CsvBooksCollectionsOfReadersGenerateCommand ourInstance = new CsvBooksCollectionsOfReadersGenerateCommand();

    public static CsvBooksCollectionsOfReadersGenerateCommand getInstance() {
        return ourInstance;
    }

    private CsvBooksCollectionsOfReadersGenerateCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/csv");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + "booksCollection.csv");
        IDocumentGenerator generator = CsvDocumentGenerator.getInstance();
        try {
            generator.generateBookCollectionsOfReaders(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "index.jsp";
    }
}
