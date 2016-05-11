package action.author;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.author.GetAuthorsCommand;
import dao.AuthorDao;
import dao.implementation.AuthorDaoImpl;
import domain.Author;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class GetAuthorsAction extends ActionSupport {
    public GetAuthorsAction() {}

    private List<Author> authors = new ArrayList<Author>();

    public String getJSONAuthors(){

        AuthorDao authorDao = AuthorDaoImpl.getInstance();
        authors = authorDao.getAuthorsList();

        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!GetAuthorsCommand.getInstance().execute(request).equals("authors.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
