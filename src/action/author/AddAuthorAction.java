package action.author;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.author.AddAuthorCommand;
import dao.AuthorDao;
import dao.implementation.AuthorDaoImpl;
import domain.Author;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class AddAuthorAction extends ActionSupport {
    public AddAuthorAction() {}
    private Author author = new Author();

    public String add_author(){
        AuthorDao authorDao = AuthorDaoImpl.getInstance();
        if(authorDao.create(author) != 0){
            System.out.println("Author Successfully added");
        } else {
            author = null;
        }

        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!AddAuthorCommand.getInstance().execute(request).equals("authors.jsp")) {
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
