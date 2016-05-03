package action.client;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.user.LoginCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class LoginAction extends ActionSupport {

    public LoginAction(){}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (LoginCommand.getInstance().execute(request).equals("login.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }

}
