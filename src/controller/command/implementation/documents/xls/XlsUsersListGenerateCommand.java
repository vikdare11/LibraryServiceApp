package controller.command.implementation.documents.xls;

import com.itextpdf.text.DocumentException;
import controller.command.DocumentGenerationCommand;
import document.IDocumentGenerator;
import document.implementation.XlsDocumentGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class XlsUsersListGenerateCommand implements DocumentGenerationCommand {
    private static XlsUsersListGenerateCommand ourInstance = new XlsUsersListGenerateCommand();

    public static XlsUsersListGenerateCommand getInstance() {
        return ourInstance;
    }

    private XlsUsersListGenerateCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + "usersList.xls");//вот здесь только имя файла поменять
        IDocumentGenerator generator = XlsDocumentGenerator.getInstance();
        try {
            generator.generateUsersList(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "index.jsp";
    }
}
