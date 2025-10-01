package kfu.itis.chuprakov.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter("/sign_up")
public class SimpleFilter implements Filter {
    private List<String> badNames = Arrays.asList("batman", "king", "rabbit");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if ("POST".equalsIgnoreCase(httpRequest.getMethod())) {
            String username = httpRequest.getParameter("login");

            if (username != null && badNames.contains(username.toLowerCase())) {
                httpResponse.sendRedirect("/sign_up");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}