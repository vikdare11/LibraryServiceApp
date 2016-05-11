package action.author;

import controller.command.implementation.author.DeleteAuthorCommand;
import com.opensymphony.xwork2.ActionSupport;
import dao.AuthorDao;
import dao.implementation.AuthorDaoImpl;
import domain.Author;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class DeleteAuthorAction extends ActionSupport {
    public DeleteAuthorAction() {}

    private Author author = new Author();

    public String delete_author(){
        AuthorDao authorDao = AuthorDaoImpl.getInstance();
        authorDao.delete(author);
        return SUCCESS;
    }
    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!DeleteAuthorCommand.getInstance().execute(request).equals("authors.jsp")) {
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
