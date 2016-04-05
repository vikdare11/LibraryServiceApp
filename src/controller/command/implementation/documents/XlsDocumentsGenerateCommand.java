package controller.command.implementation.documents;

import com.itextpdf.text.DocumentException;
import controller.command.Command;
import document.IDocumentGenerator;
import document.implementation.XlsDocumentGenerator;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class XlsDocumentsGenerateCommand implements Command {
    private static XlsDocumentsGenerateCommand ourInstance = new XlsDocumentsGenerateCommand();

    public static XlsDocumentsGenerateCommand getInstance() {
        return ourInstance;
    }

    private XlsDocumentsGenerateCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        IDocumentGenerator generator = XlsDocumentGenerator.getInstance();
        try {
            generator.generateBooksList("D://documents//booksListDocument.xls");
            generator.generateUsersList("D://documents//usersListDocument.xls");
            generator.generateBooksInfo("D://documents//booksInfoDocument.xls");
            generator.generateViewsStatistic("D://documents//booksViewStatistic.xls");
            generator.generateBookCollectionsOfReaders("D://documents//usersBookCollections.xls");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "index.jsp";
    }
}
