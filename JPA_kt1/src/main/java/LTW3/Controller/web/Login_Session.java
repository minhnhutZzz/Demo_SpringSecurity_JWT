package LTW3.Controller.web;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.persistence.EntityManager;

import java.io.IOException;

import LTW3.Configs.JPA_Config;
import LTW3.Dao.UserDao;
import LTW3.Dao.Impl.UserDaoImpl;
import LTW3.Entity.User;

@WebServlet(urlPatterns = {"/login2"})
public class Login_Session extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Login_Session() {
        super();
    }

    // POST method to handle login logic
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        // Lấy email và password từ form
        String email = req.getParameter("email");
        String passwd = req.getParameter("passwd");

        // Kiểm tra email và mật khẩu không để trống
        if (email == null || passwd == null || email.isEmpty() || passwd.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/views/login2.jsp");  // Redirect lại nếu thiếu thông tin
            return;
        }

        // Cấu hình EntityManager cho JPA
        EntityManager em = JPA_Config.getEntityManager();
        UserDao userDao = new UserDaoImpl(em);

        // Sử dụng UserDAOImpl để truy vấn người dùng
        User user = userDao.getUserByEmailAndPassword(email, passwd); // Kiểm tra email và mật khẩu

        if (user != null) {
            // Nếu đăng nhập thành công, lưu thông tin vào session
            req.getSession().setAttribute("user", user); // Lưu thông tin vào session

            // Kiểm tra quyền admin và chuyển hướng
            if (user.getIsAdmin()) {
                resp.sendRedirect(req.getContextPath() + "/views/web/admin.jsp"); // Admin redirect
            } else {
                resp.sendRedirect(req.getContextPath() + "/views/web/home.jsp");  // User redirect
            }
        } else {
            // Nếu đăng nhập không thành công, chuyển về trang đăng nhập
            resp.sendRedirect(req.getContextPath() + "/views/login2.jsp");
        }

        // Đảm bảo đóng EntityManager sau khi sử dụng
        em.close();
    }
}
