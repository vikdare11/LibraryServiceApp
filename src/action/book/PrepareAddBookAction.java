package action.book;


import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.book.PrepareAddBookCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class PrepareAddBookAction extends ActionSupport{

    public PrepareAddBookAction() {}
    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!PrepareAddBookCommand.getInstance().execute(request).equals("add_book.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }

}
