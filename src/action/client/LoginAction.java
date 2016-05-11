package action.client;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.user.LoginCommand;
import domain.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class LoginAction extends ActionSupport {

    private User user = new User();

    public LoginAction(){}



    public String loginUser(){



        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (LoginCommand.getInstance().execute(request).equals("login.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
