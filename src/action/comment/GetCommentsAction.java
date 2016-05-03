package action.comment;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.comment.GetCommentsCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class GetCommentsAction extends ActionSupport {
    public GetCommentsAction() {}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!GetCommentsCommand.getInstance().execute(request).equals("book_view.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }
}
