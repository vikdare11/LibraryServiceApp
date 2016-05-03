package action.book;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.book.AddBookCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class AddBookAction extends ActionSupport{
    public AddBookAction() {}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!AddBookCommand.getInstance().execute(request).equals("add_book.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }
}
