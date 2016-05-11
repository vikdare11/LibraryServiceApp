package action.book;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.book.ViewBookCommand;
import domain.Book;
import domain.BookViewObject;
import org.apache.struts2.ServletActionContext;
import service.Service;
import service.implementation.GetBookInfoService;

import javax.servlet.http.HttpServletRequest;

public class ViewBookAction extends ActionSupport {
    public ViewBookAction() {}

    private Book book = new Book();

    private BookViewObject bookView = new BookViewObject();
    public String get_book_view(){

        Service<Integer, BookViewObject> getBookInfoService = GetBookInfoService.getInstance();
        bookView = getBookInfoService.execute(this.book.getId());

        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (ViewBookCommand.getInstance().execute(request).equals("book_view.jsp")) {
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

    public BookViewObject getBookView() {
        return bookView;
    }

    public void setBookView(BookViewObject bookView) {
        this.bookView = bookView;
    }
}
