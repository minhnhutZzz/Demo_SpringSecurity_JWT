package LTW3.Controller.web;

import java.io.IOException;

import LTW3.Configs.JPA_Config;
import LTW3.Dao.BookDao;
import LTW3.Dao.Impl.BookDaoImpl;
import LTW3.Dao.Impl.ReviewDaoImpl;
import LTW3.Entity.Book;
import LTW3.Entity.Rating;
import LTW3.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/submitReview")
public class SubmitReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SubmitReviewServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookid = Integer.parseInt(request.getParameter("bookid"));  // Lấy bookid từ form
        String reviewText = request.getParameter("review_text");        // Lấy nội dung đánh giá
        int rating = Integer.parseInt(request.getParameter("rating"));  // Lấy điểm đánh giá

        // Lấy thông tin user từ session (nếu có)
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            // Nếu không có user trong session, chuyển hướng về trang đăng nhập
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Lấy userid từ user trong session
        int userid = user.getId();

        // Cấu hình EntityManager
        EntityManager em = JPA_Config.getEntityManager();

        // Lấy Book từ database
        BookDao bookDao = new BookDaoImpl(em);
        Book book = bookDao.getBookById(bookid);  // Lấy thông tin sách từ database

        // Tạo đối tượng Rating mới
        Rating newRating = new Rating();
        newRating.setBook(book);  // Liên kết đánh giá với sách
        newRating.setUser(user);  // Liên kết với người dùng từ session
        newRating.setReviewText(reviewText);  // Đánh giá
        newRating.setRating(rating);  // Điểm đánh giá

        // Thêm review vào cơ sở dữ liệu
        ReviewDaoImpl reviewDao = new ReviewDaoImpl(em);
        reviewDao.addRating(newRating);

        // Chuyển hướng người dùng về trang chi tiết sách sau khi gửi review thành công
        response.sendRedirect(request.getContextPath() + "/bookDetail?bookid=" + bookid);

        em.close();
    }
}
