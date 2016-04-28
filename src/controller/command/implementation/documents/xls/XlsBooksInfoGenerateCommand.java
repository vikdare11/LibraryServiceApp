package controller.command.implementation.documents.xls;

import com.itextpdf.text.DocumentException;
import controller.command.DocumentGenerationCommand;
import document.IDocumentGenerator;
import document.implementation.XlsDocumentGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class XlsBooksInfoGenerateCommand implements DocumentGenerationCommand {
    private static XlsBooksInfoGenerateCommand ourInstance = new XlsBooksInfoGenerateCommand();

    public static XlsBooksInfoGenerateCommand getInstance() {
        return ourInstance;
    }

    private XlsBooksInfoGenerateCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/xls");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + "booksInfo.xls");
        IDocumentGenerator generator = XlsDocumentGenerator.getInstance();
        try {
            generator.generateBooksInfo(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "index.jsp";
    }
}
