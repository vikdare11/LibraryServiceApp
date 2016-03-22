package controller.command.implementation.user;

import controller.command.PostCommand;
import domain.UserViewObject;
import service.Service;
import service.implementation.GetUserInfoService;

import javax.servlet.http.HttpServletRequest;

public class ViewUserCommand implements PostCommand {
    private static ViewUserCommand ourInstance = new ViewUserCommand();

    public static ViewUserCommand getInstance() {
        return ourInstance;
    }

    private ViewUserCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        Integer userId = Integer.parseInt(request.getParameter(("user_id")));
        Service<Integer, UserViewObject> getBookInfoService = GetUserInfoService.getInstance();
        UserViewObject book = getBookInfoService.execute(userId);

        request.setAttribute("userVO", book);

        return "user.jsp";
    }
}
