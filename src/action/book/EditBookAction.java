package action.book;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.book.EditBookCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class EditBookAction extends ActionSupport {
    public EditBookAction() {}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!EditBookCommand.getInstance().execute(request).equals("books.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }
}
