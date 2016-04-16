package controller.command.implementation.documents;

import com.itextpdf.text.DocumentException;
import controller.command.Command;
import document.IDocumentGenerator;
import document.implementation.XlsDocumentGenerator;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            List<String> paths = new ArrayList<>();
            generator.generateBooksList("D://documents//booksListDocument.xls");
            generator.generateUsersList("D://documents//usersListDocument.xls");
            generator.generateBooksInfo("D://documents//booksInfoDocument.xls");
            generator.generateViewsStatistic("D://documents//booksViewStatistic.xls");
            generator.generateBookCollectionsOfReaders("D://documents//usersBookCollections.xls");
            paths.add("D://documents//booksListDocument.xls");
            paths.add("D://documents//usersListDocument.xls");
            paths.add("D://documents//booksInfoDocument.xls");
            paths.add("D://documents//booksViewStatistic.xls");
            paths.add("D://documents//usersBookCollections.xls");
            request.setAttribute("paths", paths);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "index.jsp";
    }
}
