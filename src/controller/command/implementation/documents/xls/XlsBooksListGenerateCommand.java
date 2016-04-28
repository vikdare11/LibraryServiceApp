package controller.command.implementation.documents.xls;

import com.itextpdf.text.DocumentException;
import controller.command.DocumentGenerationCommand;
import document.IDocumentGenerator;
import document.implementation.XlsDocumentGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class XlsBooksListGenerateCommand implements DocumentGenerationCommand {
    private static XlsBooksListGenerateCommand ourInstance = new XlsBooksListGenerateCommand();

    public static XlsBooksListGenerateCommand getInstance() {
        return ourInstance;
    }

    private XlsBooksListGenerateCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/xls");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + "booksList.xls");
        IDocumentGenerator generator = XlsDocumentGenerator.getInstance();
        try {
            generator.generateBooksList(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "index.jsp";
    }
}
