package action.book;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.book.GetBooksCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class GetBooksAction extends ActionSupport {
    public GetBooksAction() { }

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!GetBooksCommand.getInstance().execute(request).equals("books.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }
}
