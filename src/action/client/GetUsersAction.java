package action.client;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.user.GetUsersCommand;
import dao.UserDao;
import dao.implementation.UserDaoImpl;
import domain.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class GetUsersAction extends ActionSupport {
    public GetUsersAction() {}

    private List<User> users = new ArrayList<User>();

    public String getJSONUsers(){
        UserDao userDao = UserDaoImpl.getInstance();
        this.users = userDao.getUsersList();

        return SUCCESS;
    }
    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!GetUsersCommand.getInstance().execute(request).equals("users.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
