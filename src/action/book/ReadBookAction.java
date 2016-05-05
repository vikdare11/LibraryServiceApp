package action.book;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.book.ReadBookCommand;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class ReadBookAction extends ActionSupport {
    public ReadBookAction() {}

    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        setUrl(ReadBookCommand.getInstance().execute(request));
        return "redirectPage";
    }
}
