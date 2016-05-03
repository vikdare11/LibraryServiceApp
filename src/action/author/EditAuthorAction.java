package action.author;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.author.EditAuthorCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class EditAuthorAction extends ActionSupport {
    public EditAuthorAction() {}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!EditAuthorCommand.getInstance().execute(request).equals("authors.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }
}
