package action.client;

import com.opensymphony.xwork2.ActionSupport;
import dao.UserDao;
import dao.implementation.UserDaoImpl;
import domain.Registration;
import domain.User;
import service.AuthService;
import service.implementation.RegistrationService;

/**
 * Created by Acer Aspire on 10.05.2016.
 */
public class AuthActions extends ActionSupport {

    private User user = new User();
    private AuthService authService;

    public AuthActions(){
        authService = new AuthService();
    }

    public String login(){
        if(authService.check(this.user.getLogin(), this.user.getPassword())){
            User user = authService.getCurrentUser();
            authService.setUserLogin(user.getId());
            System.out.println("Login success");
        } else {
            System.out.println("Login failed");
            this.user = null;
        }
        return SUCCESS;
    }

    public String checkLogin(){
        if(authService.getUserId() == 0){
            System.out.println("Checking failed");
            this.user = null;
        } else {
            UserDao userDao;
            userDao = UserDaoImpl.getInstance();
            this.user = userDao.read(authService.getUserId());
            System.out.println("checking success");
        }
        return SUCCESS;
    }

    public String logout(){
        authService.logout();
        return SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
