package action.documents;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.documents.csv.CsvBooksListGenerateCommand;
import controller.command.implementation.documents.pdf.PdfBooksListGenerateCommand;
import controller.command.implementation.documents.xls.XlsBooksListGenerateCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BooksListAction extends ActionSupport {
    public BooksListAction() {}

    private String docType;

    public String generate() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        docType = request.getParameter("doc_type");
        switch (docType) {
            case "csv_type":
                if (!CsvBooksListGenerateCommand.getInstance().execute(request, response).equals("index.jsp")) {
                    return ERROR;
                }
                break;
            case "pdf_type":
                if (!PdfBooksListGenerateCommand.getInstance().execute(request, response).equals("index.jsp")) {
                    return ERROR;
                }
                break;
            case "xls_type":
                if (!XlsBooksListGenerateCommand.getInstance().execute(request, response).equals("index.jsp")) {
                    return ERROR;
                }
                break;

        }
        return SUCCESS;
    }
}
