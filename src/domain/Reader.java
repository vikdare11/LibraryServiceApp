package domain;

import java.io.Serializable;

public class Reader implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int idUser;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reader reader = (Reader) o;

        if (id != reader.id) return false;
        if (idUser != reader.idUser) return false;
        return !(email != null ? !email.equals(reader.email) : reader.email != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idUser;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
