package action.author;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.author.AddAuthorCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class AddAuthorAction extends ActionSupport {
    public AddAuthorAction() {}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!AddAuthorCommand.getInstance().execute(request).equals("authors.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }
}
