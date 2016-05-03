package action.book;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.book.ViewBookCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class ViewBookAction extends ActionSupport {
    public ViewBookAction() {}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (ViewBookCommand.getInstance().execute(request).equals("book_view.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }
}
