package action.author;

import controller.command.implementation.author.DeleteAuthorCommand;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class DeleteAuthorAction extends ActionSupport {
    public DeleteAuthorAction() {}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!DeleteAuthorCommand.getInstance().execute(request).equals("authors.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }
}
