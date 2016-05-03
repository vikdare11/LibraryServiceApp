package action.book;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.book.DeleteBookCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class DeleteBookAction extends ActionSupport {
    public DeleteBookAction() {}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!DeleteBookCommand.getInstance().execute(request).equals("books.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }
}
