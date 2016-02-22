package domain;

import java.io.Serializable;

/**
 * Created by Vika on 2/22/2016.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idUser;
    private String login;
    private String password;
    private boolean idID;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIdID() {
        return idID;
    }

    public void setIdID(boolean idID) {
        this.idID = idID;
    }
}
