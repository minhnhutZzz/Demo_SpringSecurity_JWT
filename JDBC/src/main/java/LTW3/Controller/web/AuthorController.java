package LTW3.Controller.web;

import java.io.IOException;

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

@WebServlet("/author")
public class AuthorController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AuthorService authorService;

    public AuthorController() {
        super();
        AuthorDao authorDao = new AuthorDaoImpl();
        authorService = new AuthorServiceImpl(authorDao);  // Khởi tạo AuthorServiceImpl thay vì AuthorDaoImpl
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // Điều hướng đến trang tạo mới tác giả
        if ("create".equals(action)) {
            request.getRequestDispatcher("/views/web/createAuthor.jsp").forward(request, response);

        // Điều hướng đến trang cập nhật tác giả
        } else if ("update".equals(action)) {
            int authorId = Integer.parseInt(request.getParameter("authorId"));
            AuthorModel author = authorService.getAuthorById(authorId);  // Gọi từ AuthorServiceImpl
            request.setAttribute("author", author);
            request.getRequestDispatcher("/views/web/updateAuthor.jsp").forward(request, response);

        // Điều hướng đến trang xóa tác giả
        } else if ("delete".equals(action)) {
            int authorId = Integer.parseInt(request.getParameter("authorId"));
            authorService.deleteAuthor(authorId);  // Gọi từ AuthorServiceImpl
            response.sendRedirect(request.getContextPath() + "/authorList");

        } else {
            response.sendRedirect(request.getContextPath() + "/authorList");  // Nếu không có action thì chuyển về danh sách tác giả
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // Xử lý tạo tác giả mới
        if ("create".equals(action)) {
            AuthorModel author = new AuthorModel();
            author.setAuthorName(request.getParameter("authorName"));
            author.setDateOfBirth(java.sql.Date.valueOf(request.getParameter("dateOfBirth")));  // Lấy dữ liệu từ form

            authorService.addAuthor(author);  // Gọi từ AuthorServiceImpl

            response.sendRedirect(request.getContextPath() + "/authorList");

        // Xử lý cập nhật thông tin tác giả
        } else if ("update".equals(action)) {
            int authorId = Integer.parseInt(request.getParameter("authorId"));
            AuthorModel author = new AuthorModel();
            author.setAuthorId(authorId);
            author.setAuthorName(request.getParameter("authorName"));
            author.setDateOfBirth(java.sql.Date.valueOf(request.getParameter("dateOfBirth")));  // Cập nhật thông tin

            authorService.updateAuthor(author);  // Gọi từ AuthorServiceImpl

            response.sendRedirect(request.getContextPath() + "/authorList");
        }
    }
}
