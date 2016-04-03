package domain;

import java.io.Serializable;

public class Path implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String path;
    private String format;
    private int idBook;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Path path1 = (Path) o;

        if (id != path1.id) return false;
        if (idBook != path1.idBook) return false;
        if (path != null ? !path.equals(path1.path) : path1.path != null) return false;
        return !(format != null ? !format.equals(path1.format) : path1.format != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (format != null ? format.hashCode() : 0);
        result = 31 * result + idBook;
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }
}
