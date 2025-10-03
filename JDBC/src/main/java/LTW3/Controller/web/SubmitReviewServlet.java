package LTW3.Controller.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
 
import LTW3.Service.ReviewService;
import LTW3.Service.UserService;
import LTW3.Service.Impl.ReviewServiceImpl;
import LTW3.Service.Impl.UserServiceImpl;
import LTW3.Dao.ReviewDao;
import LTW3.Dao.UserDao;
import LTW3.Dao.Impl.ReviewDaoImpl;
import LTW3.Dao.Impl.UserDaoImpl;
import LTW3.Model.ReviewModel;
import LTW3.Model.UserModel;  // Sử dụng UserModel thay vì User Entity

import java.io.IOException;

@WebServlet("/submitReview")
public class SubmitReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReviewService reviewService;
    private UserService userService;

    public SubmitReviewServlet() {
        super();
        ReviewDao reviewDao = new ReviewDaoImpl();
        reviewService = new ReviewServiceImpl(reviewDao);
        UserDao userDao = new UserDaoImpl();		// Khởi tạo ReviewServiceImpl
        userService = new UserServiceImpl(userDao);      // Khởi tạo UserServiceImpl
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookid = Integer.parseInt(request.getParameter("bookid"));  // Lấy bookid từ form
        String reviewText = request.getParameter("review_text");        // Lấy nội dung đánh giá
        int rating = Integer.parseInt(request.getParameter("rating"));  // Lấy điểm đánh giá

        // Lấy thông tin user từ session (nếu có)
        HttpSession session = request.getSession();
        UserModel user = (UserModel) session.getAttribute("user");

        if (user == null) {
            // Nếu không có user trong session, chuyển hướng về trang đăng nhập
            response.sendRedirect(request.getContextPath() + "/login2.jsp");
            return;
        }

        // Tạo ReviewModel từ các thông tin lấy từ form
        ReviewModel review = new ReviewModel();
        review.setBookId(bookid);
        review.setUserId(user.getId());
        review.setRating(rating);
        review.setReviewText(reviewText);

        // Thêm review vào cơ sở dữ liệu
        if (reviewService.addReview(review)) {
            // Nếu thêm thành công, chuyển hướng đến trang chi tiết sách
            response.sendRedirect(request.getContextPath() + "/bookDetail?bookid=" + bookid);
        } else {
            // Nếu có lỗi, chuyển hướng về trang chi tiết sách
            response.sendRedirect(request.getContextPath() + "/bookDetail?bookid=" + bookid);
        }
    }
}
