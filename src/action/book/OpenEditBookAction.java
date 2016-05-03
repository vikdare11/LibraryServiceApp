package action.book;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.book.OpenEditBookCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class OpenEditBookAction extends ActionSupport {
    public OpenEditBookAction() {}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!OpenEditBookCommand.getInstance().execute(request).equals("edit_book.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }
}
