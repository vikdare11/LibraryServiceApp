package domain;

import java.io.Serializable;

public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int idBook;
    private int idReader;
    private String review;
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getIdReader() {
        return idReader;
    }

    public void setIdReader(int idReader) {
        this.idReader = idReader;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (idBook != comment.idBook) return false;
        if (idReader != comment.idReader) return false;
        if (review != null ? !review.equals(comment.review) : comment.review != null) return false;
        return !(user != null ? !user.equals(comment.user) : comment.user != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idBook;
        result = 31 * result + idReader;
        result = 31 * result + (review != null ? review.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
