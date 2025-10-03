package LTW3.Controller.web;

import java.io.IOException;
import java.util.List;

import LTW3.Dao.UserDao;
import LTW3.Dao.Impl.UserDaoImpl;
import LTW3.Model.UserModel;  // Sử dụng UserModel thay vì User Entity
import LTW3.Service.UserService;
import LTW3.Service.Impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/userList")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;

    public UserController() {
        super();
        UserDao userDao = new UserDaoImpl();
        userService = new UserServiceImpl(userDao);  // Khởi tạo UserServiceImpl
    }

    // Xử lý yêu cầu GET
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // Điều hướng đến trang tạo mới người dùng
        if ("create".equals(action)) {
            request.getRequestDispatcher("/views/web/createUser.jsp").forward(request, response);

        // Điều hướng đến trang cập nhật người dùng
        } else if ("update".equals(action)) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            UserModel user = userService.getUserById(userId); // Gọi từ UserService
            request.setAttribute("user", user);
            request.getRequestDispatcher("/views/web/updateUser.jsp").forward(request, response);

        // Điều hướng đến trang xóa người dùng
        } else if ("delete".equals(action)) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            userService.deleteUser(userId); // Gọi từ UserService
            response.sendRedirect(request.getContextPath() + "/userList");

        } else {
            // Xử lý phân trang
            int page = 1;
            int recordsPerPage = 5;  // Số lượng người dùng mỗi trang
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            int start = (page - 1) * recordsPerPage;
            List<UserModel> users = userService.getUsersPaginated(start, recordsPerPage);
            int totalRecords = userService.getTotalUsers();
            int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

            request.setAttribute("users", users);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            request.getRequestDispatcher("/views/web/userList.jsp").forward(request, response);
        }
    }

    // Xử lý yêu cầu POST
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // Xử lý tạo người dùng mới
        if ("create".equals(action)) {
            UserModel user = new UserModel();
            user.setEmail(request.getParameter("email")); // Sử dụng email thay vì username
            user.setPasswd(request.getParameter("passwd")); // Sử dụng passwd thay vì password
            user.setFullname(request.getParameter("fullname")); // Fullname thay vì fullName
            user.setSignupDate(new java.sql.Date(System.currentTimeMillis())); // Thời gian đăng ký hiện tại
            user.setLastLogin(new java.sql.Date(System.currentTimeMillis())); // Thời gian đăng nhập hiện tại
            user.setIsAdmin(false); // Mặc định là không phải admin (có thể sửa sau)

            userService.addUser(user); // Gọi từ UserService

            response.sendRedirect(request.getContextPath() + "/userList");

        // Xử lý cập nhật thông tin người dùng
        } else if ("update".equals(action)) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            UserModel user = new UserModel();
            user.setId(userId);
            user.setEmail(request.getParameter("email"));
            user.setPasswd(request.getParameter("passwd"));
            user.setFullname(request.getParameter("fullname"));
            user.setSignupDate(new java.sql.Date(System.currentTimeMillis())); // Cập nhật thời gian đăng ký mới
            user.setLastLogin(new java.sql.Date(System.currentTimeMillis())); // Cập nhật thời gian đăng nhập mới
            user.setIsAdmin(false); // Có thể thay đổi quyền admin nếu cần

            userService.updateUser(user); // Gọi từ UserService

            response.sendRedirect(request.getContextPath() + "/userList");
        }
    }
}
