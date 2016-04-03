package domain;

import java.io.Serializable;

public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private int idAuthor;
    private String description;
    private int countOfViews;
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCountOfViews() {
        return countOfViews;
    }

    public void setCountOfViews(int countOfViews) {
        this.countOfViews = countOfViews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (idAuthor != book.idAuthor) return false;
        if (countOfViews != book.countOfViews) return false;
        if (name != null ? !name.equals(book.name) : book.name != null) return false;
        if (description != null ? !description.equals(book.description) : book.description != null) return false;
        return !(author != null ? !author.equals(book.author) : book.author != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + idAuthor;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + countOfViews;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}
