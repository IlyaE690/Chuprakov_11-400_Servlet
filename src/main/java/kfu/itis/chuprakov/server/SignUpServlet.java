package kfu.itis.chuprakov.server;

import kfu.itis.chuprakov.dao.UserDao;
import kfu.itis.chuprakov.dao.impl.UserDaoImpl;
import kfu.itis.chuprakov.entity.User;
import kfu.itis.chuprakov.service.UserService;
import kfu.itis.chuprakov.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignUp", urlPatterns = "/sign_up")
public class SignUpServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

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
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login == null || login.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            req.getRequestDispatcher("sign_up.ftl").forward(req, resp);
            return;
        }

        if (userService.userExists(login)) {
            req.getRequestDispatcher("sign_up.ftl").forward(req, resp);
            return;
        }

        userService.registerUser(name, lastname, login, password);

        resp.sendRedirect("/login");
    }
}