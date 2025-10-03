package LTW3.Controller.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import LTW3.Dao.UserDao_23110282;
import LTW3.Dao.Impl.UserDaoImpl_23110282;
import LTW3.Model.UserModel_23110282;  // Sử dụng UserModel thay vì User (Entity)
import LTW3.Service.UserService_23110282;
import LTW3.Service.Impl.UserServiceImpl_23110282;

import java.io.IOException;

@WebServlet(urlPatterns = {"/registerSession"})
public class RegisterSession_23110282 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserService_23110282 userService;

    public RegisterSession_23110282() {
        super();
        UserDao_23110282 userDao = new UserDaoImpl_23110282();
        userService = new UserServiceImpl_23110282(userDao); // Khởi tạo UserServiceImpl
    }

    // POST method to handle registration logic
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        // Lấy thông tin từ form đăng ký
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        // Kiểm tra mật khẩu có trùng khớp không
        if (!password.equals(confirmPassword)) {
            resp.sendRedirect(req.getContextPath() + "/views/RegisterSession_23110282.jsp");
            return;
        }

        // Kiểm tra xem email đã tồn tại chưa từ UserService
        if (userService.isEmailExist(email)) {
            resp.sendRedirect(req.getContextPath() + "/views/RegisterSession_23110282.jsp");
            return;
        }

        // Tạo đối tượng UserModel và thêm vào CSDL
        UserModel_23110282 user = new UserModel_23110282();
        user.setEmail(email);
        user.setFullname(fullname);
        user.setPasswd(password);  // Mã hóa mật khẩu trước khi lưu
        user.setIsAdmin(false);  // Mặc định là người dùng bình thường

        // Lưu người dùng vào cơ sở dữ liệu thông qua UserService
        if (userService.addUser(user)) {
            // Lưu người dùng vào session nếu đăng ký thành công
            HttpSession session = req.getSession();
            session.setAttribute("user", user);  // Lưu thông tin vào session

            // Chuyển hướng về trang đăng nhập
            resp.sendRedirect(req.getContextPath() + "/views/login_23110282.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/views/RegisterSession_23110282.jsp");
        }
    }
}
