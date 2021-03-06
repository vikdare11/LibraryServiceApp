package controller.command.implementation.user;

import controller.command.Command;
import domain.Registration;
import org.apache.commons.codec.digest.DigestUtils;
import service.Service;
import service.implementation.RegistrationService;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {

    private static final RegistrationCommand instance = new RegistrationCommand();

    private RegistrationCommand() {}

    public static RegistrationCommand getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = new String("");
        String salt = "eio4nrvy3874cy2x873ryc873xt263rcvojt";

        if (validateRequest(request)) {

            Service<Registration, Boolean> registrationService = RegistrationService.getInstance();

            Registration registrationData = new Registration();

            registrationData.setLogin(request.getParameter("login"));
            registrationData.setPassword(DigestUtils.md5Hex(request.getParameter("password") + salt));
            registrationData.setEmail(request.getParameter("email"));

            try {
                if (registrationService.execute(registrationData)){
                    page = "login.jsp";
                }
                else {
                    page = "registration.jsp" + "?" + "message" + "=user_exist";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else {
            page = "registration.jsp";
        }

        return page;
    }

    private boolean validateRequest(HttpServletRequest request) {
        if ((!request.getParameter("login").isEmpty()) &&
                (!request.getParameter("password").isEmpty()) &&
                (!request.getParameter("email").isEmpty())) {
            return true;
        } else {
            return false;
        }
    }
}
