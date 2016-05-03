package action.client;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.user.LogoutCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class LogoutAction extends ActionSupport {

    public LogoutAction(){}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!LogoutCommand.getInstance().execute(request).equals("login.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }
}
