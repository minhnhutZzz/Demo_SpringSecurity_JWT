package LTW3.Controller.web;

import java.io.IOException;
import java.util.List;

import LTW3.Model.RatingModel;  // Sử dụng RatingModel thay vì Rating (Entity)
import LTW3.Model.UserModel;    // Sử dụng UserModel thay vì User (Entity)
import LTW3.Service.ReviewService;
import LTW3.Service.UserService;
import LTW3.Service.Impl.ReviewServiceImpl;
import LTW3.Service.Impl.UserServiceImpl;
import LTW3.Dao.ReviewDao;
import LTW3.Dao.UserDao;
import LTW3.Dao.Impl.ReviewDaoImpl;
import LTW3.Dao.Impl.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bookDetail")
public class BookDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReviewService reviewService;
    private UserService userService;

    public BookDetailServlet() {
        super();
        // Khởi tạo các service với DAO thích hợp
        ReviewDao reviewDao = new ReviewDaoImpl();
        reviewService = new ReviewServiceImpl(reviewDao);
        UserDao userDao = new UserDaoImpl();
        userService = new UserServiceImpl(userDao);  // Khởi tạo UserServiceImpl
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookIdStr = request.getParameter("bookid");

        // Kiểm tra nếu bookId không hợp lệ
        if (bookIdStr == null || bookIdStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/login1.jsp");  // Chuyển đến trang lỗi nếu không có bookId
            return;
        }

        try {
            int bookId = Integer.parseInt(bookIdStr);  // Chuyển bookId từ String sang int

            // Lấy danh sách đánh giá của cuốn sách từ ReviewService
            List<RatingModel> ratings = reviewService.getRatingsByBook(bookId);

            // Lấy thông tin người dùng cho mỗi đánh giá
            for (RatingModel rating : ratings) {
                UserModel user = userService.getUserById(rating.getUserId()); // Lấy thông tin người dùng theo userId từ UserService
                rating.setUser(user); // Gán thông tin người dùng vào rating
            }

            // Gửi danh sách đánh giá và thông tin người dùng vào JSP
            request.setAttribute("ratings", ratings);
            request.getRequestDispatcher("/views/web/bookDetail.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // Nếu bookId không phải là số hợp lệ, chuyển hướng về trang lỗi
            response.sendRedirect(request.getContextPath() + "/login1.jsp");  // Trang lỗi nếu bookId không hợp lệ
        }
    }
}
