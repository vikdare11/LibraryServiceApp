package action.author;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.author.EditAuthorCommand;
import dao.AuthorDao;
import dao.implementation.AuthorDaoImpl;
import domain.Author;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class EditAuthorAction extends ActionSupport {
    public EditAuthorAction() {}

    private Author author = new Author();

    public String edit_author(){

        AuthorDao authorDao = AuthorDaoImpl.getInstance();
        authorDao.update(author);

        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!EditAuthorCommand.getInstance().execute(request).equals("authors.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
