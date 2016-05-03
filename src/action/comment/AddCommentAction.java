package action.comment;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.comment.AddCommentCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class AddCommentAction extends ActionSupport{
    public AddCommentAction() {}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!AddCommentCommand.getInstance().execute(request).equals("book_view.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }
}
