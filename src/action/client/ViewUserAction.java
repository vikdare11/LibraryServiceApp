package action.client;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.user.ViewUserCommand;
import domain.User;
import domain.UserViewObject;
import org.apache.struts2.ServletActionContext;
import service.Service;
import service.implementation.GetUserInfoService;

import javax.servlet.http.HttpServletRequest;

public class ViewUserAction extends ActionSupport {
    public ViewUserAction() {}

    private User user = new User();

    private UserViewObject userView = new UserViewObject();

    public String getJSONUser(){
        Service<Integer, UserViewObject> getUserInfoService = GetUserInfoService.getInstance();
        userView = getUserInfoService.execute(user.getId());

        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!ViewUserCommand.getInstance().execute(request).equals("user.jsp")) {
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

    public UserViewObject getUserView() {
        return userView;
    }

    public void setUserView(UserViewObject userView) {
        this.userView = userView;
    }
}
