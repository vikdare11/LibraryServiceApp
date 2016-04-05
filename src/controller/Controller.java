package controller;

import com.itextpdf.text.DocumentException;
import controller.command.Command;
import controller.command.implementation.author.*;
import controller.command.implementation.book.*;
import controller.command.implementation.comment.AddCommentCommand;
import controller.command.implementation.comment.DeleteCommentCommand;
import controller.command.implementation.user.*;
import document.IDocumentGenerator;
import document.implementation.XlsDocumentGenerator;
import domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
public class Controller extends HttpServlet {

    private static List<String> availableOperations = new ArrayList<>();
    private static List<String> availableAdminOperations = new ArrayList<>();
    private static List<String> availableUserOperations = new ArrayList<>();

    static {
        availableOperations.add("login");
        availableOperations.add("register");
        availableOperations.add("get_books");
        availableOperations.add("get_authors");
        availableOperations.add("open_author");

        availableAdminOperations.add("add_author");
        availableAdminOperations.add("delete_author");
        availableAdminOperations.add("open_edit_author");
        availableAdminOperations.add("edit_author");
        availableAdminOperations.add("view_book");
        availableAdminOperations.add("add_comment");
        availableAdminOperations.add("get_users");
        availableAdminOperations.add("open_user");
        availableAdminOperations.add("delete_book");
        availableAdminOperations.add("delete_user");
        availableAdminOperations.add("delete_comment");
        availableAdminOperations.add("prepare_add_book");
        availableAdminOperations.add("add_book");
        availableAdminOperations.add("logout");
        availableAdminOperations.add("add_book_to_reader_collection");
        availableAdminOperations.add("remove_book_from_reader_collection");
        availableAdminOperations.add("edit_user");
        availableAdminOperations.add("read_online");
        availableAdminOperations.add("edit_book");
        availableAdminOperations.add("open_edit_book");


        availableUserOperations.add("view_book");
        availableUserOperations.add("add_comment");
        availableUserOperations.add("get_users");
        availableUserOperations.add("open_user");
        availableUserOperations.add("logout");
        availableUserOperations.add("add_book_to_reader_collection");
        availableUserOperations.add("remove_book_from_reader_collection");
        availableUserOperations.add("read_online");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter("command");
        String page = null;
        Command command = null;
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
                case "delete_user" : {
                    command = DeleteUserCommand.getInstance();
                    break;
                }
                case "delete_comment" : {
                    command = DeleteCommentCommand.getInstance();
                    break;
                }
                case "prepare_add_book" : {
                    command = PrepareAddBookCommand.getInstance();
                    break;
                }
                case "add_book" : {
                    command = AddBookCommand.getInstance();
                    break;
                }
                case "logout" : {
                    command = LogoutCommand.getInstance();
                    break;
                }
                case "add_book_to_reader_collection" : {
                    command = AddBookToReaderCollectionCommand.getInstance();
                    break;
                }
                case "remove_book_from_reader_collection" : {
                    command = RemoveBookFromReaderCollection.getInstance();
                    break;
                }
                case "edit_user" : {
                    command = EditUserCommand.getInstance();
                    break;
                }
                case "edit_book" : {
                    command = EditBookCommand.getInstance();
                    break;
                }
            }

            boolean access = true;
            if (command != null) {
                if (!availableOperations.contains(commandName)) {
                    User user = (User) req.getSession().getAttribute("user");

                    if (user.isAdmin()) {
                        access = availableAdminOperations.contains(commandName);
                    }
                    else {
                        access = availableUserOperations.contains(commandName);
                    }

                }
                if (!access) {
                    req.getSession().invalidate();
                    resp.sendRedirect("login.jsp");
                    return;
                }
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
        String commandName = req.getParameter("command");
        String page = null;
        Command command = null;
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
                    IDocumentGenerator generator = XlsDocumentGenerator.getInstance();
                    try {
                        generator.generateBooksList();
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
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
                case "read_online" : {
                    command = ReadBookCommand.getInstance();
                    break;
                }
                case "delete_user" : {
                    command = DeleteUserCommand.getInstance();
                    break;
                }
                case "delete_comment" : {
                    command = DeleteCommentCommand.getInstance();
                    break;
                }
                case "prepare_add_book" : {
                    command = PrepareAddBookCommand.getInstance();
                    break;
                }
                case "add_book" : {
                    command = AddBookCommand.getInstance();
                    break;
                }
                case "logout" : {
                    command = LogoutCommand.getInstance();
                    break;
                }
                case "add_book_to_reader_collection" : {
                    command = AddBookToReaderCollectionCommand.getInstance();
                    break;
                }
                case "remove_book_from_reader_collection" : {
                    command = RemoveBookFromReaderCollection.getInstance();
                    break;
                }
                case "edit_user" : {
                    command = EditUserCommand.getInstance();
                    break;
                }
                case "open_edit_book" : {
                    command = OpenEditBookCommand.getInstance();
                    break;
                }
            }

            boolean access = true;
            if (command != null) {
                if (!availableOperations.contains(commandName)) {
                    User user = (User) req.getSession().getAttribute("user");
                    if (user != null) {
                        if (user.isAdmin()) {
                            access = availableAdminOperations.contains(commandName);
                        }
                        else {
                            access = availableUserOperations.contains(commandName);
                        }
                    }
                    else {
                        access = false;
                    }

                }
                if (!access) {
                    req.getSession().invalidate();
                    resp.sendRedirect("login.jsp");
                    return;
                }
                page = command.execute(req);
            }
        }
        if (command.getClass() == LogoutCommand.class) {
            resp.setHeader("Cache-Control", "no-cache, no-store");
            resp.setHeader("Pragma", "no-cache");
            resp.sendRedirect("login.jsp");
            return;
        }
        if (page != null) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(page);
            requestDispatcher.forward(req, resp);
        }
    }
}
