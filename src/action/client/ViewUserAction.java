package action.client;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.user.ViewUserCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class ViewUserAction extends ActionSupport {
    public ViewUserAction() {}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!ViewUserCommand.getInstance().execute(request).equals("user.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }
}
