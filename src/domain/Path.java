package domain;

import java.io.Serializable;

/**
 * Created by Vika on 2/22/2016.
 */
public class Path implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idPath;
    private String path;
    private String format;
    private int idBook;

    public int getIdPath() {
        return idPath;
    }

    public void setIdPath(int idPath) {
        this.idPath = idPath;
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
