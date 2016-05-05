package action.documents;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.documents.csv.CsvUsersListGenerateCommand;
import controller.command.implementation.documents.pdf.PdfUsersListGenerateCommand;
import controller.command.implementation.documents.xls.XlsUsersListGenerateCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsersListAction extends ActionSupport {
    public UsersListAction() {}

    private String docType;

    public String generate() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        docType = request.getParameter("doc_type");
        switch (docType) {
            case "csv_type":
                if (!CsvUsersListGenerateCommand.getInstance().execute(request, response).equals("index.jsp")) {
                    return ERROR;
                }
                break;
            case "pdf_type":
                if (!PdfUsersListGenerateCommand.getInstance().execute(request, response).equals("index.jsp")) {
                    return ERROR;
                }
                break;
            case "xls_type":
                if (!XlsUsersListGenerateCommand.getInstance().execute(request, response).equals("index.jsp")) {
                    return ERROR;
                }
                break;

        }
        return SUCCESS;
    }
}
