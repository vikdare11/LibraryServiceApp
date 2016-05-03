package action.comment;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.comment.DeleteCommentCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class DeleteCommentAction extends ActionSupport {
    public DeleteCommentAction() {}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!DeleteCommentCommand.getInstance().execute(request).equals("book_view.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }

}
