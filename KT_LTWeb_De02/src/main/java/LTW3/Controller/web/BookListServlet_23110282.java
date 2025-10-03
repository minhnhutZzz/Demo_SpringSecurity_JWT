package LTW3.Controller.web;

import java.io.IOException;
import java.util.List;

import LTW3.Model.BookModel_23110282;  // Sử dụng BookModel thay vì Book (Entity)
import LTW3.Service.BookService_23110282;
import LTW3.Service.Impl.BookServiceImpl_23110282;
import LTW3.Dao.BookDao_23110282;
import LTW3.Dao.Impl.BookDaoImpl_23110282;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bookList")
public class BookListServlet_23110282 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private BookService_23110282 bookService;

    public BookListServlet_23110282() {
        super();
        BookDao_23110282 bookDao = new BookDaoImpl_23110282();
        bookService = new BookServiceImpl_23110282(bookDao);  // Khởi tạo BookServiceImpl thay vì BookDaoImpl
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

        // Lấy danh sách sách với phân trang từ BookService
        List<BookModel_23110282> books = bookService.getBooksPaginated(start, recordsPerPage);

        // Lấy tổng số sách để tính toán số trang từ BookService
        int totalRecords = bookService.getTotalBooks();
        int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

        // Gửi thông tin phân trang và danh sách sách đến view
        request.setAttribute("books", books);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        // Chuyển đến JSP để hiển thị
        request.getRequestDispatcher("/views/web/bookList_23110282.jsp").forward(request, response);
    }
}
