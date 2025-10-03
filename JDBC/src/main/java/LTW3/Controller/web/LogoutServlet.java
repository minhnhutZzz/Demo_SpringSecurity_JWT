package LTW3.Controller.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Hủy session của người dùng
        request.getSession().invalidate();

        // Xóa cookie
        Cookie cookie = new Cookie("email", "");
        cookie.setMaxAge(0); // Xóa cookie
        response.addCookie(cookie);

        // Chuyển hướng về trang đăng nhập
        response.sendRedirect(request.getContextPath() + "/views/logout.jsp");
    }
}
