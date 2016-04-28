package controller.command.implementation.documents.xls;

import com.itextpdf.text.DocumentException;
import controller.command.DocumentGenerationCommand;
import document.IDocumentGenerator;
import document.implementation.XlsDocumentGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class XlsViewsStatisticGenerateCommand implements DocumentGenerationCommand {
    private static XlsViewsStatisticGenerateCommand ourInstance = new XlsViewsStatisticGenerateCommand();

    public static XlsViewsStatisticGenerateCommand getInstance() {
        return ourInstance;
    }

    private XlsViewsStatisticGenerateCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/xls");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + "viewStatistics.xls");
        IDocumentGenerator generator = XlsDocumentGenerator.getInstance();
        try {
            generator.generateViewsStatistic(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "index.jsp";
    }
}
