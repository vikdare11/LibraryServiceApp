package action.documents;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.documents.csv.CsvBooksCollectionsOfReadersGenerateCommand;
import controller.command.implementation.documents.pdf.PdfBooksCollectionsOfReadersGenerateCommand;
import controller.command.implementation.documents.xls.XlsBooksCollectionsOfReadersGenerateCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookCollectionOfReaderAction extends ActionSupport{
    public BookCollectionOfReaderAction() {}

    private String docType;

    public String generate() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        docType = request.getParameter("doc_type");
        switch (docType) {
            case "csv_type":
                if (!CsvBooksCollectionsOfReadersGenerateCommand.getInstance().execute(request, response).equals("index.jsp")) {
                    return ERROR;
                }
                break;
            case "pdf_type":
                if (!PdfBooksCollectionsOfReadersGenerateCommand.getInstance().execute(request, response).equals("index.jsp")) {
                    return ERROR;
                }
                break;
            case "xls_type":
                if (!XlsBooksCollectionsOfReadersGenerateCommand.getInstance().execute(request, response).equals("index.jsp")) {
                    return ERROR;
                }
                break;

        }
        return SUCCESS;
    }
}
