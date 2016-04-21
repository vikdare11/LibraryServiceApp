package controller.command.implementation.user;

import controller.command.Command;
import domain.UserViewObject;
import service.Service;
import service.implementation.GetUserInfoService;

import javax.servlet.http.HttpServletRequest;

public class ViewUserCommand implements Command {
    private static ViewUserCommand ourInstance = new ViewUserCommand();

    public static ViewUserCommand getInstance() {
        return ourInstance;
    }

    private ViewUserCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        Integer userId = Integer.parseInt(request.getParameter("user_id"));
        Service<Integer, UserViewObject> getUserInfoService = GetUserInfoService.getInstance();
        UserViewObject user = getUserInfoService.execute(userId);

        request.setAttribute("userVO", user);

        return "user.jsp";
    }
}
