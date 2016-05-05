package action.author;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.author.OpenEditAuthorCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class OpenEditAuthorAction extends ActionSupport {
    public OpenEditAuthorAction() {}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!OpenEditAuthorCommand.getInstance().execute(request).equals("edit_author.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }
}
