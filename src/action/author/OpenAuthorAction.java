package action.author;

import controller.command.implementation.author.OpenAuthorCommand;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class OpenAuthorAction extends ActionSupport {
    public OpenAuthorAction() {}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!OpenAuthorCommand.getInstance().execute(request).equals("author.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }
}
