package controller.command.implementation.documents.csv;

import com.itextpdf.text.DocumentException;
import controller.command.DocumentGenerationCommand;
import document.IDocumentGenerator;
import document.implementation.CsvDocumentGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CsvViewsStatisticGenerateCommand implements DocumentGenerationCommand {
    private static CsvViewsStatisticGenerateCommand ourInstance = new CsvViewsStatisticGenerateCommand();

    public static CsvViewsStatisticGenerateCommand getInstance() {
        return ourInstance;
    }

    private CsvViewsStatisticGenerateCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IDocumentGenerator generator = CsvDocumentGenerator.getInstance();
        try {
            response.setContentType("application/csv");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + "viewStatistics.csv");
            generator.generateViewsStatistic(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "index.jsp";
    }
}
