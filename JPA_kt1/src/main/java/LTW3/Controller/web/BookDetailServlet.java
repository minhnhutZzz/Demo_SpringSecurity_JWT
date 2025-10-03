package LTW3.Controller.web;

import java.io.IOException;
import java.util.List;

import LTW3.Configs.JPA_Config;
import LTW3.Dao.BookDao;
import LTW3.Dao.Impl.BookDaoImpl;
import LTW3.Dao.Impl.ReviewDaoImpl;
import LTW3.Entity.Book;
import LTW3.Entity.Rating;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bookDetail")
public class BookDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BookDetailServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookid = Integer.parseInt(request.getParameter("bookid"));

        // Cấu hình EntityManager
        EntityManager em = JPA_Config.getEntityManager();

        // Truy vấn thông tin sách
        BookDao bookDao = new BookDaoImpl(em);
        Book book = bookDao.getBookById(bookid);  // Lấy thông tin sách theo bookid

        // Lấy danh sách đánh giá của sách
        ReviewDaoImpl reviewDao = new ReviewDaoImpl(em);
        List<Rating> ratings = reviewDao.getRatingsByBook(book);  // Lấy các review từ bảng rating

        // Gửi dữ liệu đến JSP
        request.setAttribute("book", book);
        request.setAttribute("ratings", ratings);
        request.getRequestDispatcher("/views/web/bookDetail.jsp").forward(request, response);

        em.close();
    }
}

