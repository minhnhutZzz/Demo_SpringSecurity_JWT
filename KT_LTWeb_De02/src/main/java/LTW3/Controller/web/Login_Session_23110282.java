package LTW3.Controller.web;

import java.io.IOException;

import LTW3.Dao.UserDao_23110282;
import LTW3.Dao.Impl.UserDaoImpl_23110282;
import LTW3.Model.UserModel_23110282;  // Sử dụng UserModel thay vì User (Entity)
import LTW3.Service.UserService_23110282;
import LTW3.Service.Impl.UserServiceImpl_23110282;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/login"})
public class Login_Session_23110282 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserService_23110282 userService;

    public Login_Session_23110282() {
        super();
        UserDao_23110282 userDao = new UserDaoImpl_23110282();
        userService = new UserServiceImpl_23110282(userDao);  // Khởi tạo UserServiceImpl
    }

    // POST method to handle login logic
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        // Lấy email và mật khẩu từ form
        String email = req.getParameter("email");
        String passwd = req.getParameter("passwd");

        // Kiểm tra email và mật khẩu không để trống
        if (email == null || passwd == null || email.isEmpty() || passwd.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/views/login_23110282.jsp");  // Redirect lại nếu thiếu thông tin
            return;
        }

        // Lấy người dùng từ UserService
        UserModel_23110282 user = userService.getUserByEmailAndPassword(email, passwd); // Kiểm tra email và mật khẩu

        if (user != null) {
            // Nếu đăng nhập thành công, lưu thông tin vào session
            req.getSession().setAttribute("user", user); // Lưu thông tin vào session

            // Kiểm tra quyền admin và chuyển hướng
            if (user.isIsAdmin()) {
                resp.sendRedirect(req.getContextPath() + "/views/web/admin_23110282.jsp"); // Admin redirect
            } else {
                resp.sendRedirect(req.getContextPath() + "/views/web/home_23110282.jsp");  // User redirect
            }
        } else {
            // Nếu đăng nhập không thành công, chuyển về trang đăng nhập
            resp.sendRedirect(req.getContextPath() + "/views/login_23110282.jsp");
        }
    }
}
