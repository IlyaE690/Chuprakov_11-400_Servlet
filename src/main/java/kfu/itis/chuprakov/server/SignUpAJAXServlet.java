package kfu.itis.chuprakov.server;

import kfu.itis.chuprakov.service.UserService;
import kfu.itis.chuprakov.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/ajax/sign_up")
public class SignUpAJAXServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        String login = req.getParameter("login");

        if (userService.getUserByLogin(login) != null) {
            resp.getWriter().write("Login already exists!");
        } else{
            resp.getWriter().write("Login is satisfying.");
        }


    }
}
