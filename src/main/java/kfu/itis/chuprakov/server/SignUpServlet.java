package kfu.itis.chuprakov.server;

import kfu.itis.chuprakov.service.FileUploadService;
import kfu.itis.chuprakov.service.UserService;
import kfu.itis.chuprakov.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(name = "SignUp", urlPatterns = "/sign_up")
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class SignUpServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();
    private final FileUploadService fileUploadService = new FileUploadService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("sign_up.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Part part = req.getPart("image");

        String filename = null;

        if (part != null && part.getSize() > 0 && part.getSubmittedFileName() != null &&
                !part.getSubmittedFileName().isEmpty()) {

            filename = fileUploadService.uploadFile(part);
        }

        if (login == null || login.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            req.setAttribute("error", "Login and password are required!");
            req.getRequestDispatcher("sign_up.ftl").forward(req, resp);
            return;
        }

        if (userService.userExists(login)) {
            req.setAttribute("error", "Login already exists!");
            req.getRequestDispatcher("sign_up.ftl").forward(req, resp);
            return;
        }

        userService.registerUser(name, lastname, login, password, filename);

        resp.sendRedirect("/login");
    }
}