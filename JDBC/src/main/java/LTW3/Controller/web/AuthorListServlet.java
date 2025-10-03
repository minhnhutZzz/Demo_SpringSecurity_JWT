package LTW3.Controller.web;

import java.io.IOException;
import java.util.List;

import LTW3.Model.AuthorModel;  // Sử dụng AuthorModel thay vì Author (Entity)
import LTW3.Service.AuthorService;
import LTW3.Service.Impl.AuthorServiceImpl;
import LTW3.Dao.AuthorDao;
import LTW3.Dao.Impl.AuthorDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/authorList")
public class AuthorListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AuthorService authorService;

    public AuthorListServlet() {
        super();
        AuthorDao authorDao = new AuthorDaoImpl();
        authorService = new AuthorServiceImpl(authorDao);  // Khởi tạo AuthorServiceImpl thay vì AuthorDaoImpl
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 5;  // Số lượng tác giả mỗi trang

        // Lấy tham số "page" từ URL nếu có
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Tính toán chỉ số bắt đầu của trang
        int start = (page - 1) * recordsPerPage;

        // Lấy danh sách tác giả với phân trang từ AuthorService
        List<AuthorModel> authors = authorService.getAuthorsPaginated(start, recordsPerPage);

        // Lấy tổng số tác giả để tính toán số trang từ AuthorService
        int totalRecords = authorService.getTotalAuthors();
        int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

        // Gửi thông tin phân trang và danh sách tác giả đến view
        request.setAttribute("authors", authors);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        // Chuyển đến JSP để hiển thị
        request.getRequestDispatcher("/views/web/authorList.jsp").forward(request, response);
    }
}
