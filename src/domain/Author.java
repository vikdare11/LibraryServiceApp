package domain;

import java.io.Serializable;

/**
 * Created by Vika on 2/22/2016.
 */
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idAuthor;

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    private String name;
    private String surname;

}
