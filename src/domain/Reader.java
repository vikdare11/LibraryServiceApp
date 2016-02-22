package domain;

import java.io.Serializable;

/**
 * Created by Vika on 2/22/2016.
 */
public class Reader implements Serializable {
    private static final long serialVersionUID = 1L;
    private int idReader;
    private int idUser;
    private String email;

    public int getIdReader() {
        return idReader;
    }

    public void setIdReader(int idReader) {
        this.idReader = idReader;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
