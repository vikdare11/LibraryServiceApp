package controller;

import controller.command.PostCommand;
import controller.command.implementation.author.*;
import controller.command.implementation.book.DeleteBookCommand;
import controller.command.implementation.book.GetBooksByAuthorCommand;
import controller.command.implementation.book.GetBooksCommand;
import controller.command.implementation.book.ViewBookCommand;
import controller.command.implementation.comment.AddCommentCommand;
import controller.command.implementation.user.GetUsersCommand;
import controller.command.implementation.user.LoginCommand;
import controller.command.implementation.user.RegistrationCommand;
import controller.command.implementation.user.ViewUserCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
public class Controller extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter("command");
        String page = null;
        PostCommand command = null;
        if (commandName != null) {
            switch (commandName) {
                case "get_authors" : {
                    command = GetAuthorsCommand.getInstance();
                    break;
                }
                case "open_author" : {
                    command = GetBooksByAuthorCommand.getInstance();
                    break;
                }
                case "edit_author" : {
                    command = EditAuthorCommand.getInstance();
                    break;
                }
                case "delete_author" : {
                    command = DeleteAuthorCommand.getInstance();
                    break;
                }
                case "add_author" : {
                    command = AddAuthorCommand.getInstance();
                    break;
                }
                case "register" : {
                    command = RegistrationCommand.getInstance();
                    break;
                }
                case "login" : {
                    command = LoginCommand.getInstance();
                    break;
                }
                case "get_books" : {
                    command = GetBooksCommand.getInstance();
                    break;
                }
                case "view_book" : {
                    command = ViewBookCommand.getInstance();
                    break;
                }
                case "open_edit_author" : {
                    command = OpenAuthorCommand.getInstance();
                    break;
                }
                case "add_comment" : {
                    command = AddCommentCommand.getInstance();
                    break;
                }
                case "get_users" : {
                    command = GetUsersCommand.getInstance();
                    break;
                }
                case "open_user" : {
                    command = ViewUserCommand.getInstance();
                    break;
                }
                case "delete_book" : {
                    command = DeleteBookCommand.getInstance();
                    break;
                }
            }
            if (command != null) {
                page = command.execute(req);
            }
        }
        if (page != null) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(page);
            requestDispatcher.forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
