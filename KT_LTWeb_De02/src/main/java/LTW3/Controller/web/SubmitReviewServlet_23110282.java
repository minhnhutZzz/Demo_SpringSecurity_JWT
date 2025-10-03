package LTW3.Controller.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import LTW3.Service.RatingService_23110282;
import LTW3.Service.Impl.RatingServiceImpl_23110282;
import LTW3.Dao.RatingDao_23110282;
import LTW3.Dao.Impl.RatingDaoImpl_23110282;
import LTW3.Model.RatingModel_23110282;
import LTW3.Model.UserModel_23110282;

import java.io.IOException;

@WebServlet("/submitReview")
public class SubmitReviewServlet_23110282 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RatingService_23110282 ratingService;

    public SubmitReviewServlet_23110282() {
        RatingDao_23110282 ratingDao = new RatingDaoImpl_23110282();
        ratingService = new RatingServiceImpl_23110282(ratingDao);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String bookIdStr = request.getParameter("bookid");
        String reviewText = request.getParameter("review_text");
        String ratingStr = request.getParameter("rating");

        if (bookIdStr == null || bookIdStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }

        int bookid = Integer.parseInt(bookIdStr);
        int ratingValue = 0;
        if (ratingStr != null && !ratingStr.isEmpty()) {
            try {
                ratingValue = Integer.parseInt(ratingStr);
            } catch (NumberFormatException e) {
                ratingValue = 0; // fallback nếu nhập sai
            }
        }

        HttpSession session = request.getSession();
        UserModel_23110282 user = (UserModel_23110282) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/views/web/login_23110282.jsp");
            return;
        }

        RatingModel_23110282 rating = new RatingModel_23110282();
        rating.setBookId(bookid);
        rating.setUserId(user.getId());
        rating.setRating(ratingValue);
        rating.setReviewText(reviewText);

        ratingService.addRating(rating);

        response.sendRedirect(request.getContextPath() + "/bookDetail?bookid=" + bookid);
    }
}
