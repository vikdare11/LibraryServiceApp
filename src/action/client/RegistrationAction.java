package action.client;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.user.RegistrationCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class RegistrationAction extends ActionSupport{
    public RegistrationAction() {}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (RegistrationCommand.getInstance().execute(request).equals("login.jsp")) {
            return SUCCESS;
        }
        return ERROR;
    }
}
