package controller.command.implementation.documents.csv;

import com.itextpdf.text.DocumentException;
import controller.command.DocumentGenerationCommand;
import document.IDocumentGenerator;
import document.implementation.CsvDocumentGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CsvUsersListGenerateCommand implements DocumentGenerationCommand {
    private static CsvUsersListGenerateCommand ourInstance = new CsvUsersListGenerateCommand();

    public static CsvUsersListGenerateCommand getInstance() {
        return ourInstance;
    }

    private CsvUsersListGenerateCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IDocumentGenerator generator = CsvDocumentGenerator.getInstance();
        try {
            response.setContentType("application/csv");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + "usersList.csv");
            generator.generateUsersList(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "index.jsp";
    }
}
