package action.author;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.author.GetAuthorsCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class GetAuthorsAction extends ActionSupport {
    public GetAuthorsAction() {}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!GetAuthorsCommand.getInstance().execute(request).equals("authors.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }
}
