package action.book;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.book.EditBookCommand;
import dao.BookDao;
import dao.implementation.BookDaoImpl;
import domain.Book;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class EditBookAction extends ActionSupport {
    public EditBookAction() {}

    private Book book = new Book();


    public String edit_book(){
        BookDao bookDao = BookDaoImpl.getInstance();
        bookDao.update(book);

        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!EditBookCommand.getInstance().execute(request).equals("books.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
