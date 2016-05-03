package action.book;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.book.GetBooksByAuthorCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class GetBooksByAuthorAction extends ActionSupport {
    public GetBooksByAuthorAction() {}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!GetBooksByAuthorCommand.getInstance().execute(request).equals("author.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }
}
