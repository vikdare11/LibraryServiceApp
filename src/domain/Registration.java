package domain;

/**
 * Created by Vika on 3/7/2016.
 */
import java.io.Serializable;

public class Registration implements Serializable {

    private static final long serialVersionUID = 1L;

    private String login;
    private String password;
    private String email;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
