package controller.command.implementation.comment;

import controller.command.PostCommand;
import domain.Comment;
import domain.User;
import service.implementation.AddCommentService;

import javax.servlet.http.HttpServletRequest;

public class AddCommentCommand implements PostCommand {
    private static AddCommentCommand ourInstance = new AddCommentCommand();

    public static AddCommentCommand getInstance() {
        return ourInstance;
    }

    private AddCommentCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {

        int readerId = ((User)request.getSession().getAttribute("user")).getId();
        int idBook = Integer.parseInt(request.getParameter("book_id"));
        String review = request.getParameter("review");

        Comment comment = new Comment();
        comment.setIdBook(idBook);
        comment.setReview(review);
        comment.setIdReader(readerId);

        AddCommentService addCommentService = AddCommentService.getInstance();
        addCommentService.execute(comment);

        return GetCommentsCommand.getInstance().execute(request);
    }
}
