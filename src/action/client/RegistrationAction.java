package action.client;

import com.opensymphony.xwork2.ActionSupport;
import controller.command.implementation.user.RegistrationCommand;
import domain.Registration;
import domain.User;
import org.apache.struts2.ServletActionContext;
import service.implementation.RegistrationService;

import javax.servlet.http.HttpServletRequest;

public class RegistrationAction extends ActionSupport{
    public RegistrationAction() {}

    private Registration registration = new Registration();

    public String registrateUser(){


        RegistrationService service = RegistrationService.getInstance();
        if(service.execute(registration)){
            System.out.println("Registered");
        } else {
            System.out.println("Can't be registered");
        }


        return SUCCESS;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (RegistrationCommand.getInstance().execute(request).equals("login.jsp")) {
            return SUCCESS;
        }
        return ERROR;
    }
}
