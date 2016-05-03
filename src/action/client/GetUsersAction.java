package action.client;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.user.GetUsersCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class GetUsersAction extends ActionSupport {
    public GetUsersAction() {}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!GetUsersCommand.getInstance().execute(request).equals("users.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }
}
