package domain;

import java.io.Serializable;
import java.util.List;

public class BookViewObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private Book book;
    private Author author;
    private List<Comment> listOfComments;
    private Path readPath;

    public List<Path> getDownloadPaths() {
        return downloadPaths;
    }

    public void setDownloadPaths(List<Path> downloadPaths) {
        this.downloadPaths = downloadPaths;
    }

    private List<Path> downloadPaths;


    public Path getReadPath() {
        return readPath;
    }

    public void setReadPath(Path readPath) {
        this.readPath = readPath;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Comment> getListOfComments() {
        return listOfComments;
    }

    public void setListOfComments(List<Comment> listOfComments) {
        this.listOfComments = listOfComments;
    }
}
