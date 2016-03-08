package controller;

import controller.command.Command;
import controller.command.implementation.author.*;
import domain.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@MultipartConfig
public class Controller extends HttpServlet {

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
                    command = OpenAuthorCommand.getInstance();
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
    }
}
