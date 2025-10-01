package kfu.itis.chuprakov.server;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignUp", urlPatterns = "/sign_up")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            req.getRequestDispatcher("sign_up.ftl").forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login == null || login.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            req.getRequestDispatcher("sign_up.ftl").forward(req, resp);
            return;
        }

        if (UserStorage.userExists(login)) {
            req.getRequestDispatcher("sign_up.ftl").forward(req, resp);
            return;
        }


        UserStorage.addUser(login, password);

        resp.sendRedirect("/login");
    }
}