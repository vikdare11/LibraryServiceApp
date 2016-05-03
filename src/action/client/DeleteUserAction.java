package action.client;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.user.DeleteUserCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserAction extends ActionSupport {
    public DeleteUserAction() {}
    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!DeleteUserCommand.getInstance().execute(request).equals("users.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }
}
