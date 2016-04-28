package controller.command.implementation.documents.pdf;

import com.itextpdf.text.DocumentException;
import controller.command.DocumentGenerationCommand;
import document.implementation.PdfDocumentGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PdfUsersListGenerateCommand implements DocumentGenerationCommand {
    private static PdfUsersListGenerateCommand ourInstance = new PdfUsersListGenerateCommand();

    public static PdfUsersListGenerateCommand getInstance() {
        return ourInstance;
    }

    private PdfUsersListGenerateCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PdfDocumentGenerator generator = (PdfDocumentGenerator) PdfDocumentGenerator.getInstance();
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + "usersList.pdf");
            generator.setIMAGE_BACK(request.getServletContext().getRealPath("/image/background.jpg"));
            generator.generateUsersList(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "index.jsp";
    }
}
