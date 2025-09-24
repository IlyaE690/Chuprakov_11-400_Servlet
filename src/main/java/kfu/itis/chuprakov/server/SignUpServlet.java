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
        resp.sendRedirect("sign_up.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login == null || login.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            resp.sendRedirect("/sign_up");
            return;
        }

        if (UserStorage.userExists(login)) {
            resp.sendRedirect("/sign_up");
            return;
        }


        UserStorage.addUser(login, password);

        resp.sendRedirect("/login");
    }
}