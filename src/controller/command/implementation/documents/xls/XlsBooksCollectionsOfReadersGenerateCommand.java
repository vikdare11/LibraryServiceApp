package controller.command.implementation.documents.xls;

import com.itextpdf.text.DocumentException;
import controller.command.DocumentGenerationCommand;
import document.IDocumentGenerator;
import document.implementation.XlsDocumentGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class XlsBooksCollectionsOfReadersGenerateCommand implements DocumentGenerationCommand {
    private static XlsBooksCollectionsOfReadersGenerateCommand ourInstance = new XlsBooksCollectionsOfReadersGenerateCommand();

    public static XlsBooksCollectionsOfReadersGenerateCommand getInstance() {
        return ourInstance;
    }

    private XlsBooksCollectionsOfReadersGenerateCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/xls");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + "booksCollection.xls");
        IDocumentGenerator generator = XlsDocumentGenerator.getInstance();
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
