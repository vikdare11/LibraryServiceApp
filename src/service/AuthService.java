package service;

/**
 * Created by Acer Aspire on 10.05.2016.
 */
import com.opensymphony.xwork2.ActionContext;
import dao.UserDao;
import dao.implementation.UserDaoImpl;
import domain.User;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class AuthService  implements SessionAware {
    private User currentUser;
    private Map authSession;
    public AuthService(){
        this.authSession = ActionContext.getContext().getSession();
    }


    public Map setUserLogin(int id){
        this.authSession.put("id", id);
        return this.authSession;
    }

    public int getUserId(){
        if (this.authSession.get("id") != null)
            return (int) this.authSession.get("id");
        return 0;
    }

    public boolean check(String login, String password){
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        UserDao userDao;
        userDao = UserDaoImpl.getInstance();
        try {
            int idUser = userDao.findIdUser(user);
            if (idUser != -1) {
                this.currentUser = userDao.read(idUser);
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

    }



    public User getCurrentUser(){
        return this.currentUser;
    }

    public void setCurrentUser(User user){
        this.currentUser = user;
    }

    public void logout(){
        this.authSession.clear();
        setCurrentUser(null);
    }

    public void setSession(Map<String, Object> sessionMap) {
        this.authSession = authSession;
    }
}