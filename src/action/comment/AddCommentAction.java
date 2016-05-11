package action.comment;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.comment.AddCommentCommand;
import dao.CommentDao;
import dao.implementation.CommentDaoImpl;
import domain.Comment;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

public class AddCommentAction extends ActionSupport{
    public AddCommentAction() {}

    private Comment comment = new Comment();

    public String add_comment(){
        CommentDao commentDao = CommentDaoImpl.getInstance();
        commentDao.create(this.comment);

        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!AddCommentCommand.getInstance().execute(request).equals("book_view.jsp")) {
            return ERROR;
        }
        return SUCCESS;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
