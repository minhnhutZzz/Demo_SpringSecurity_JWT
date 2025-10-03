package LTW3.Controller.web;

import java.io.IOException;

import java.math.BigDecimal;
import java.math.BigInteger;

import LTW3.Configs.JPA_Config;
import LTW3.Dao.BookDao;
import LTW3.Dao.Impl.BookDaoImpl;
import LTW3.Entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/book")
public class BookController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BookController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String action = request.getParameter("action");

        // Điều hướng đến trang tạo mới sách
        if ("create".equals(action)) {
            request.getRequestDispatcher("/views/web/createBook.jsp").forward(request, response);

        // Điều hướng đến trang cập nhật sách
        } else if ("update".equals(action)) {
            int bookid = Integer.parseInt(request.getParameter("bookid"));
            EntityManager em = JPA_Config.getEntityManager();
            BookDao bookDao = new BookDaoImpl(em);
            Book book = bookDao.getBookById(bookid);
            request.setAttribute("book", book);
            request.getRequestDispatcher("/views/web/updateBook.jsp").forward(request, response);

        // Điều hướng đến trang xóa sách
        } else if ("delete".equals(action)) {
            int bookid = Integer.parseInt(request.getParameter("bookid"));
            EntityManager em = JPA_Config.getEntityManager();
            BookDao bookDao = new BookDaoImpl(em);
            bookDao.deleteBook(bookid);
            em.close();
            response.sendRedirect(request.getContextPath() + "/bookList");

        } else {
            response.sendRedirect(request.getContextPath() + "/bookList"); 
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // Xử lý tạo sách mới
        if ("create".equals(action)) {
            // Tạo đối tượng Book mới
            Book book = new Book();
            book.setTitle(request.getParameter("title"));
            book.setIsbn(new BigInteger(request.getParameter("isbn")));
            book.setPublisher(request.getParameter("publisher"));
            book.setPrice(new BigDecimal(request.getParameter("price")));  
            book.setDescription(request.getParameter("description"));
            
            // Xử lý publishDate (chuyển từ String sang java.sql.Date)
            String publishDateStr = request.getParameter("publishDate");
            try {
                java.sql.Date publishDate = java.sql.Date.valueOf(publishDateStr);
                book.setPublishDate(publishDate);  // Cập nhật publishDate
            } catch (IllegalArgumentException e) {
                // Xử lý lỗi nếu định dạng ngày không đúng
                response.sendRedirect(request.getContextPath() + "/bookList");
                return;
            }

            book.setCoverImage(request.getParameter("coverImage"));
            book.setQuantity(Integer.parseInt(request.getParameter("quantity")));

            // Khởi tạo EntityManager và gọi dao để thêm sách mới vào cơ sở dữ liệu
            EntityManager em = JPA_Config.getEntityManager();
            BookDao bookDao = new BookDaoImpl(em);
            bookDao.addBook(book);
            
            // Đóng EntityManager sau khi sử dụng
            em.close();

            // Chuyển hướng về danh sách sách
            response.sendRedirect(request.getContextPath() + "/bookList");


        // Xử lý cập nhật thông tin sách
        } else if ("update".equals(action)) {
            int bookid = Integer.parseInt(request.getParameter("bookid"));
            Book book = new Book();
            book.setBookid(bookid);

            // Sửa lại kiểu dữ liệu theo yêu cầu
            book.setTitle(request.getParameter("title"));
            
            // ISBN là kiểu int
            book.setIsbn(new BigInteger(request.getParameter("isbn")));  // Chuyển đổi sang int
            
            // Publisher là String, giữ nguyên
            book.setPublisher(request.getParameter("publisher"));

            // Price là BigDecimal
            book.setPrice(new BigDecimal(request.getParameter("price")));  // Chuyển đổi sang BigDecimal

            // Description là String, giữ nguyên
            book.setDescription(request.getParameter("description"));

            // PublishDate là Date
            String publishDateStr = request.getParameter("publishDate");
            try {
                // Chuyển đổi publishDate từ String sang java.sql.Date
                java.sql.Date publishDate = java.sql.Date.valueOf(publishDateStr);
                book.setPublishDate(publishDate);  // Cập nhật publishDate
            } catch (IllegalArgumentException e) {
                // Xử lý lỗi nếu định dạng ngày không đúng
                response.sendRedirect(request.getContextPath() + "/bookList");
                return;
            }

            // CoverImage là String (URL của ảnh)
            book.setCoverImage(request.getParameter("coverImage"));

            // Quantity là kiểu int
            book.setQuantity(Integer.parseInt(request.getParameter("quantity")));

            // Lưu cập nhật vào cơ sở dữ liệu
            EntityManager em = JPA_Config.getEntityManager();
            BookDao bookDao = new BookDaoImpl(em);
            bookDao.updateBook(book);
            em.close();

            response.sendRedirect(request.getContextPath() + "/bookList");
        }

    }
}

