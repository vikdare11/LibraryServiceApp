package action.book;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.book.AddBookCommand;
import dao.BookDao;
import dao.implementation.BookDaoImpl;
import domain.Book;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class AddBookAction extends ActionSupport{
    public AddBookAction() {}

    private Book book = new Book();


    public String add_author_book(){
        BookDao bookDao = BookDaoImpl.getInstance();
            if(bookDao.create(book) == -1){
                book = null;
                System.out.println("Book adding failed");
            } else {
                System.out.println("Book added");
            }
        return SUCCESS;
    }
    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!AddBookCommand.getInstance().execute(request).equals("add_book.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }

    public void setBook(Book book){
        this.book = book;
    }

    public Book getBook(){
        return this.book;
    }
}
