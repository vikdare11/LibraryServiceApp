package domain;

import java.io.Serializable;
import java.util.List;

public class UserViewObject implements Serializable {
    private static final long serialVersionUID = 1L;

    private String login;
    private String email;
    private List<Book> bookCollection;

    public List<Book> getBookCollection() {
        return bookCollection;
    }

    public void setBookCollection(List<Book> bookCollection) {
        this.bookCollection = bookCollection;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
