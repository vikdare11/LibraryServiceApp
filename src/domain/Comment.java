package domain;

import java.io.Serializable;

/**
 * Created by Vika on 2/22/2016.
 */
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idComment;
    private int idBook;
    private int idUser;
    private String review;

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
