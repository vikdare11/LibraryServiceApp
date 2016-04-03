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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserViewObject that = (UserViewObject) o;

        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return !(bookCollection != null ? !bookCollection.equals(that.bookCollection) : that.bookCollection != null);

    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (bookCollection != null ? bookCollection.hashCode() : 0);
        return result;
    }
}
