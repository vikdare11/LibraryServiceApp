package action.book;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.book.GetBooksCommand;
import dao.BookDao;
import dao.implementation.BookDaoImpl;
import domain.Book;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetBooksAction extends ActionSupport {
    public GetBooksAction() { }

    private Book[] books;

    public String getJSONBooks(){
        System.out.println("Books was requested");
       BookDao bookDao = BookDaoImpl.getInstance();
        List<Book> list = bookDao.getBooksList();
        this.books = list.toArray(new Book[list.size()]);
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!GetBooksCommand.getInstance().execute(request).equals("books.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }
}
