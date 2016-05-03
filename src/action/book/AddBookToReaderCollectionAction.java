package action.book;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.book.AddBookToReaderCollectionCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class AddBookToReaderCollectionAction extends ActionSupport{
    public AddBookToReaderCollectionAction() {}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!AddBookToReaderCollectionCommand.getInstance().execute(request).equals("user.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }
}
