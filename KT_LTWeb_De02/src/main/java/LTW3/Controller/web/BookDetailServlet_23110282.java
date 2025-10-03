package LTW3.Controller.web;

import java.io.IOException;
import java.util.List;

import LTW3.Model.BookModel_23110282;
import LTW3.Model.RatingModel_23110282;
import LTW3.Model.UserModel_23110282;
import LTW3.Service.BookService_23110282;
import LTW3.Service.RatingService_23110282;
import LTW3.Service.UserService_23110282;
import LTW3.Service.Impl.BookServiceImpl_23110282;
import LTW3.Service.Impl.RatingServiceImpl_23110282;
import LTW3.Service.Impl.UserServiceImpl_23110282;
import LTW3.Dao.BookDao_23110282;
import LTW3.Dao.RatingDao_23110282;
import LTW3.Dao.UserDao_23110282;
import LTW3.Dao.Impl.BookDaoImpl_23110282;
import LTW3.Dao.Impl.RatingDaoImpl_23110282;
import LTW3.Dao.Impl.UserDaoImpl_23110282;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bookDetail")
public class BookDetailServlet_23110282 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RatingService_23110282 ratingService;
    private UserService_23110282 userService;
    private BookService_23110282 bookService;

    public BookDetailServlet_23110282() {
        RatingDao_23110282 ratingDao = new RatingDaoImpl_23110282();
        ratingService = new RatingServiceImpl_23110282(ratingDao);

        UserDao_23110282 userDao = new UserDaoImpl_23110282();
        userService = new UserServiceImpl_23110282(userDao);

        BookDao_23110282 bookDao = new BookDaoImpl_23110282();
        bookService = new BookServiceImpl_23110282(bookDao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String bookIdStr = request.getParameter("bookid");
        if (bookIdStr == null || bookIdStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }

        try {
            int bookId = Integer.parseInt(bookIdStr);

            // Lấy chi tiết sách
            BookModel_23110282 book = bookService.getBookById(bookId);
            if (book == null) {
                response.sendRedirect(request.getContextPath() + "/error.jsp");
                return;
            }
            request.setAttribute("book", book);

            // Lấy danh sách đánh giá + thông tin user
            List<RatingModel_23110282> ratings = ratingService.getRatingsByBook(bookId);
            for (RatingModel_23110282 rating : ratings) {
                UserModel_23110282 user = userService.getUserById(rating.getUserId());
                rating.setUser(user);
            }
            request.setAttribute("ratings", ratings);

            // Forward tới JSP
            request.getRequestDispatcher("/views/web/bookDetail_23110282.jsp")
                   .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
