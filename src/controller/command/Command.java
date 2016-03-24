package controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Vika on 3/1/2016.
 */
public interface Command {
    String execute(HttpServletRequest request);
}
