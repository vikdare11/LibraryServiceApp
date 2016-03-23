package controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@MultipartConfig
public class BookDownloadController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookPath = req.getParameter("bookPath");
        String savingFileName = bookPath.split("/")[1];

        resp.setContentType("text/plain");
        resp.setHeader("Content-Disposition",
                "attachment;filename="+savingFileName);
        ServletContext ctx = getServletContext();

        InputStream is = ctx.getResourceAsStream(bookPath);

        try {
            int read=0;
            byte[] bytes = new byte[1024];
            OutputStream os = resp.getOutputStream();

            while((read = is.read(bytes))!= -1){
                os.write(bytes, 0, read);
            }
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
