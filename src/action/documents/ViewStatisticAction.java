package action.documents;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.documents.csv.CsvViewsStatisticGenerateCommand;
import controller.command.implementation.documents.pdf.PdfViewsStatisticGenerateCommand;
import controller.command.implementation.documents.xls.XlsViewsStatisticGenerateCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewStatisticAction extends ActionSupport{
    public ViewStatisticAction() {}
    private String docType;

    public String generate() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        docType = request.getParameter("doc_type");
        switch (docType) {
            case "csv_type":
                if (!CsvViewsStatisticGenerateCommand.getInstance().execute(request, response).equals("index.jsp")) {
                    return ERROR;
                }
                break;
            case "pdf_type":
                if (!PdfViewsStatisticGenerateCommand.getInstance().execute(request, response).equals("index.jsp")) {
                    return ERROR;
                }
                break;
            case "xls_type":
                if (!XlsViewsStatisticGenerateCommand.getInstance().execute(request, response).equals("index.jsp")) {
                    return ERROR;
                }
                break;

        }
        return SUCCESS;
    }
}
