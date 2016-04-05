package controller.command.implementation.documents;

import com.itextpdf.text.DocumentException;
import controller.command.Command;
import document.IDocumentGenerator;
import document.implementation.CsvDocumentGenerator;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CsvDocumentsGenerateCommand implements Command {
    private static CsvDocumentsGenerateCommand ourInstance = new CsvDocumentsGenerateCommand();

    public static CsvDocumentsGenerateCommand getInstance() {
        return ourInstance;
    }

    private CsvDocumentsGenerateCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        IDocumentGenerator generator = CsvDocumentGenerator.getInstance();
        try {
            generator.generateBooksList("D://documents//booksListDocument.csv");
            generator.generateUsersList("D://documents//usersListDocument.csv");
            generator.generateBooksInfo("D://documents//booksInfoDocument.csv");
            generator.generateViewsStatistic("D://documents//booksViewStatistic.csv");
            generator.generateBookCollectionsOfReaders("D://documents//usersBookCollections.csv");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "index.jsp";
    }
}
