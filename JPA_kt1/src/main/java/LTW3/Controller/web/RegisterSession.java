package LTW3.Controller.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.io.IOException;

import LTW3.Configs.JPA_Config;
import LTW3.Entity.User;
import LTW3.Dao.UserDao;
import LTW3.Dao.Impl.UserDaoImpl;

@WebServlet(urlPatterns = {"/registerSession"})
public class RegisterSession extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterSession() {
        super();
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
            resp.sendRedirect(req.getContextPath() + "/views/registerSession.jsp");
            return;
        }

        // Kiểm tra xem email đã tồn tại chưa
        if (isEmailExist(email)) {
            resp.sendRedirect(req.getContextPath() + "/views/registerSession.jsp");
            return;
        }

        // Tạo đối tượng User và thêm vào CSDL
        User user = new User();
        user.setEmail(email);
        user.setFullname(fullname);
        user.setPasswd(password);  // Bạn có thể mã hóa mật khẩu trước khi lưu
        user.setIsAdmin(false);  // Mặc định là người dùng bình thường

        // Khởi tạo EntityManager
        EntityManager em = JPA_Config.getEntityManager();
        UserDao userDao = new UserDaoImpl(em);

        // Thực hiện thêm người dùng vào cơ sở dữ liệu
        if (addUser(userDao, user)) {
            // Lưu người dùng vào session nếu đăng ký thành công
            HttpSession session = req.getSession();
            session.setAttribute("user", user);  // Lưu thông tin vào session

            // Chuyển hướng về trang chính
            resp.sendRedirect(req.getContextPath() + "/views/login2.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/views/registerSession.jsp");
        }
    }

    private boolean isEmailExist(String email) {
        // Kiểm tra email đã tồn tại trong cơ sở dữ liệu
        EntityManager em = JPA_Config.getEntityManager();
        UserDao userDao = new UserDaoImpl(em);
        User existingUser = userDao.getUserByEmail(email);
        return existingUser != null;
    }

    private boolean addUser(UserDao userDao, User user) {
        // Thực hiện lưu người dùng vào cơ sở dữ liệu
        EntityManager em = JPA_Config.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        
        try {
            transaction.begin();
            userDao.addUser(user);  // Sử dụng phương thức addUser của UserDaoImpl
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }
}
