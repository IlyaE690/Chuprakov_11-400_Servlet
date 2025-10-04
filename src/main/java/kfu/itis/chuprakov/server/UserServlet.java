package kfu.itis.chuprakov.server;


import kfu.itis.chuprakov.dao.UserDao;
import kfu.itis.chuprakov.dao.impl.UserDaoImpl;
import kfu.itis.chuprakov.dto.UserDto;
import kfu.itis.chuprakov.service.UserService;
import kfu.itis.chuprakov.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "User", urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserDto> users = userService.getAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("users.ftl").forward(req, resp);
    }
}