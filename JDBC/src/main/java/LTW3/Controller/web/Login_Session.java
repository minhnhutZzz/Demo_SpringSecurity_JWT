package LTW3.Controller.web;

import java.io.IOException;

import LTW3.Dao.UserDao;
import LTW3.Dao.Impl.UserDaoImpl;
import LTW3.Model.UserModel;  // Sử dụng UserModel thay vì User (Entity)
import LTW3.Service.UserService;
import LTW3.Service.Impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/login2"})
public class Login_Session extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserService userService;

    public Login_Session() {
        super();
        UserDao userDao = new UserDaoImpl();
        userService = new UserServiceImpl(userDao);  // Khởi tạo UserServiceImpl
    }

    // POST method to handle login logic
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        // Lấy email và mật khẩu từ form
        String email = req.getParameter("email");
        String passwd = req.getParameter("passwd");

        // Kiểm tra email và mật khẩu không để trống
        if (email == null || passwd == null || email.isEmpty() || passwd.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/views/login2.jsp");  // Redirect lại nếu thiếu thông tin
            return;
        }

        // Lấy người dùng từ UserService
        UserModel user = userService.getUserByEmailAndPassword(email, passwd); // Kiểm tra email và mật khẩu

        if (user != null) {
            // Nếu đăng nhập thành công, lưu thông tin vào session
            req.getSession().setAttribute("user", user); // Lưu thông tin vào session

            // Kiểm tra quyền admin và chuyển hướng
            if (user.isIsAdmin()) {
                resp.sendRedirect(req.getContextPath() + "/views/web/admin.jsp"); // Admin redirect
            } else {
                resp.sendRedirect(req.getContextPath() + "/views/web/home.jsp");  // User redirect
            }
        } else {
            // Nếu đăng nhập không thành công, chuyển về trang đăng nhập
            resp.sendRedirect(req.getContextPath() + "/views/login2.jsp");
        }
    }
}
