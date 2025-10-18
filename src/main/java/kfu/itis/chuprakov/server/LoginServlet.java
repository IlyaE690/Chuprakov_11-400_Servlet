package kfu.itis.chuprakov.server;

import kfu.itis.chuprakov.entity.User;
import kfu.itis.chuprakov.service.UserService;
import kfu.itis.chuprakov.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            resp.sendRedirect("/main");
            return;
        }

        req.getRequestDispatcher("login.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (userService.authenticate(login, password)) {
            User user = userService.getUserByLogin(login);

            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(60 * 60);

            Cookie cookie = new Cookie("user", login);
            cookie.setMaxAge(60 * 60);
            resp.addCookie(cookie);

            resp.sendRedirect("/main");
        } else {
            try {
                req.setAttribute("error", "Invalid login or password");
                req.getRequestDispatcher("login.ftl").forward(req, resp);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }
    }
}