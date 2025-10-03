package LTW3.Controller.web;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.persistence.EntityManager;

import java.io.IOException;
import java.util.List;

import LTW3.Configs.JPA_Config;
import LTW3.Dao.BookDao;
import LTW3.Dao.Impl.BookDaoImpl;
import LTW3.Entity.Book;

@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BookListServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 5;  // Số lượng sách mỗi trang

        // Lấy tham số "page" từ URL nếu có
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Tính toán chỉ số bắt đầu của trang
        int start = (page - 1) * recordsPerPage;

        // Cấu hình EntityManager và DAO
        EntityManager em = JPA_Config.getEntityManager();
        BookDao bookDao = new BookDaoImpl(em);

        // Lấy danh sách sách với phân trang
        List<Book> books = bookDao.getBooksPaginated(start, recordsPerPage);

        // Lấy tổng số sách để tính toán số trang
        int totalRecords = bookDao.getTotalBooks();
        int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

        // Gửi thông tin phân trang và danh sách sách đến view
        request.setAttribute("books", books);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        // Chuyển đến JSP để hiển thị
        request.getRequestDispatcher("/views/web/bookList.jsp").forward(request, response);

        em.close();
    }
}
