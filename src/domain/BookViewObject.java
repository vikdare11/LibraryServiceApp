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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookViewObject that = (BookViewObject) o;

        if (book != null ? !book.equals(that.book) : that.book != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (listOfComments != null ? !listOfComments.equals(that.listOfComments) : that.listOfComments != null)
            return false;
        if (readPath != null ? !readPath.equals(that.readPath) : that.readPath != null) return false;
        return !(downloadPaths != null ? !downloadPaths.equals(that.downloadPaths) : that.downloadPaths != null);

    }

    @Override
    public int hashCode() {
        int result = book != null ? book.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (listOfComments != null ? listOfComments.hashCode() : 0);
        result = 31 * result + (readPath != null ? readPath.hashCode() : 0);
        result = 31 * result + (downloadPaths != null ? downloadPaths.hashCode() : 0);
        return result;
    }
}
