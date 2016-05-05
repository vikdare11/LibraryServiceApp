package action.documents;


import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.documents.csv.CsvBooksInfoGenerateCommand;
import controller.command.implementation.documents.pdf.PdfBooksInfoGenerateCommand;
import controller.command.implementation.documents.xls.XlsBooksInfoGenerateCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookInfoAction extends ActionSupport{
    public BookInfoAction() {}

    private String docType;

    public String generate() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        docType = request.getParameter("doc_type");
        switch (docType) {
            case "csv_type":
                if (!CsvBooksInfoGenerateCommand.getInstance().execute(request, response).equals("index.jsp")) {
                    return ERROR;
                }
                break;
            case "pdf_type":
                if (!PdfBooksInfoGenerateCommand.getInstance().execute(request, response).equals("index.jsp")) {
                    return ERROR;
                }
                break;
            case "xls_type":
                if (!XlsBooksInfoGenerateCommand.getInstance().execute(request, response).equals("index.jsp")) {
                    return ERROR;
                }
                break;

        }
        return SUCCESS;
    }
}
