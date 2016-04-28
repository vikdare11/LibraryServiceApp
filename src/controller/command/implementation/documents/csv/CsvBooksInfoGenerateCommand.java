package controller.command.implementation.documents.csv;

import com.itextpdf.text.DocumentException;
import controller.command.DocumentGenerationCommand;
import document.IDocumentGenerator;
import document.implementation.CsvDocumentGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CsvBooksInfoGenerateCommand implements DocumentGenerationCommand {
    private static CsvBooksInfoGenerateCommand ourInstance = new CsvBooksInfoGenerateCommand();

    public static CsvBooksInfoGenerateCommand getInstance() {
        return ourInstance;
    }

    private CsvBooksInfoGenerateCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IDocumentGenerator generator = CsvDocumentGenerator.getInstance();
        try {
            response.setContentType("application/csv");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + "booksInfo.csv");
            generator.generateBooksInfo(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "index.jsp";
    }
}
