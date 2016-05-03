package action.client;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.user.EditUserCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class EditUserAction extends ActionSupport {
    public EditUserAction() {}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (EditUserCommand.getInstance().execute(request).equals("users.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }
}
