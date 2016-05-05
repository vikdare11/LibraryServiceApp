package action.book;


import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BookDownloadAction extends ActionSupport{
    public BookDownloadAction() {}

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        String bookPath = request.getParameter("bookPath");
        download(request, response, bookPath);
        return SUCCESS;
    }

    private void download(HttpServletRequest req, HttpServletResponse resp, String path) {

        String savingFileName = path.split("/")[1];

        path = "WEB-INF/" + path;

        resp.setContentType("text/plain");
        resp.setHeader("Content-Disposition",
                "attachment;filename=" + savingFileName);
        ServletContext ctx = req.getServletContext();

        InputStream is = ctx.getResourceAsStream(path);

        try {
            int read = 0;
            byte[] bytes = new byte[1024];
            OutputStream os = resp.getOutputStream();

            while ((read = is.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
