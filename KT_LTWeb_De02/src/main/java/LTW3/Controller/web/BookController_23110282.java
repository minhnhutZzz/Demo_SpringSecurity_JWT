package LTW3.Controller.web;

import java.io.IOException;
import java.math.BigDecimal;

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

@WebServlet("/book")
public class BookController_23110282 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookService_23110282 bookService;

    public BookController_23110282() {
        super();
        BookDao_23110282 bookDao = new BookDaoImpl_23110282();
        bookService = new BookServiceImpl_23110282(bookDao); 
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // Điều hướng đến trang tạo mới sách
        if ("create".equals(action)) {
            request.getRequestDispatcher("/views/web/createBook_23110282.jsp").forward(request, response);

        // Điều hướng đến trang cập nhật sách
        } else if ("update".equals(action)) {
            int bookId = Integer.parseInt(request.getParameter("bookid"));
            BookModel_23110282 book = bookService.getBookById(bookId); 
            request.setAttribute("book", book);
            request.getRequestDispatcher("/views/web/updateBook_23110282.jsp").forward(request, response);

        // Điều hướng đến trang xóa sách
        } else if ("delete".equals(action)) {
            int bookId = Integer.parseInt(request.getParameter("bookid"));
            bookService.deleteBook(bookId); // Gọi từ BookServiceImpl
            response.sendRedirect(request.getContextPath() + "/bookList");

        } else {
            response.sendRedirect(request.getContextPath() + "/bookList"); // Nếu không có action thì chuyển về danh sách sách
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // Xử lý tạo sách mới
        if ("create".equals(action)) {
            BookModel_23110282 book = new BookModel_23110282();
            book.setTitle(request.getParameter("title"));
            book.setIsbn(new BigDecimal(request.getParameter("isbn")));
            book.setPublisher(request.getParameter("publisher"));
            book.setPrice(new BigDecimal(request.getParameter("price")));
            book.setDescription(request.getParameter("description"));

            String publishDateStr = request.getParameter("publishDate");
            try {
                java.sql.Date publishDate = java.sql.Date.valueOf(publishDateStr);
                book.setPublishDate(publishDate);
            } catch (IllegalArgumentException e) {
                response.sendRedirect(request.getContextPath() + "/bookList");
                return;
            }

            book.setCoverImage(request.getParameter("coverImage"));
            book.setQuantity(Integer.parseInt(request.getParameter("quantity")));

            bookService.addBook(book); // Gọi từ BookServiceImpl

            response.sendRedirect(request.getContextPath() + "/bookList");

        // Xử lý cập nhật thông tin sách
        } else if ("update".equals(action)) {
            int bookId = Integer.parseInt(request.getParameter("bookid"));
            BookModel_23110282 book = new BookModel_23110282();
            book.setBookid(bookId);
            book.setTitle(request.getParameter("title"));
            book.setIsbn(new BigDecimal(request.getParameter("isbn")));
            book.setPublisher(request.getParameter("publisher"));
            book.setPrice(new BigDecimal(request.getParameter("price")));
            book.setDescription(request.getParameter("description"));

            String publishDateStr = request.getParameter("publishDate");
            try {
                java.sql.Date publishDate = java.sql.Date.valueOf(publishDateStr);
                book.setPublishDate(publishDate);
            } catch (IllegalArgumentException e) {
                response.sendRedirect(request.getContextPath() + "/bookList");
                return;
            }

            book.setCoverImage(request.getParameter("coverImage"));
            book.setQuantity(Integer.parseInt(request.getParameter("quantity")));

            bookService.updateBook(book); // Gọi từ BookServiceImpl

            response.sendRedirect(request.getContextPath() + "/bookList");
        }
    }
}
