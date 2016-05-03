package action.book;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.book.RemoveBookFromReaderCollectionCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class RemoveBookFromReaderCollectionAction extends ActionSupport{
    public RemoveBookFromReaderCollectionAction() {}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!RemoveBookFromReaderCollectionCommand.getInstance().execute(request).equals("user.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }
}
