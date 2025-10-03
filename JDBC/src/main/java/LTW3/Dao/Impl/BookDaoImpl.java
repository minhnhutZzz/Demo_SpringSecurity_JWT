package LTW3.Dao.Impl;

import LTW3.Dao.BookDao;
import LTW3.Model.BookModel;
import LTW3.Model.RatingModel;
import LTW3.Util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {

    // Lấy tất cả các sách
    @Override
	public List<BookModel> getAllBooks() {
        List<BookModel> books = new ArrayList<>();
        String query = "SELECT * FROM books";  // SQL lấy tất cả sách

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            // Duyệt qua các kết quả và thêm vào danh sách sách
            while (rs.next()) {
                BookModel book = new BookModel();
                book.setBookid(rs.getInt("bookid"));
                book.setTitle(rs.getString("title"));
                book.setIsbn(rs.getBigDecimal("isbn"));
                book.setPublisher(rs.getString("publisher"));
                book.setPrice(rs.getBigDecimal("price"));
                book.setDescription(rs.getString("description"));
                book.setPublishDate(rs.getDate("publish_date"));
                book.setCoverImage(rs.getString("cover_image"));
                book.setQuantity(rs.getInt("quantity"));
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }

    // Lấy sách với phân trang
  
    @Override
	public List<BookModel> getBooksPaginated(int start, int recordsPerPage) {
        List<BookModel> books = new ArrayList<>();
        String query = "SELECT * FROM books ORDER BY bookid OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, start);  // Vị trí bắt đầu (OFFSET)
            stmt.setInt(2, recordsPerPage);  // Số lượng sách mỗi trang (FETCH NEXT)

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    BookModel book = new BookModel();
                    book.setBookid(rs.getInt("bookid"));
                    book.setTitle(rs.getString("title"));
                    book.setIsbn(rs.getBigDecimal("isbn"));
                    book.setPublisher(rs.getString("publisher"));
                    book.setPrice(rs.getBigDecimal("price"));
                    book.setDescription(rs.getString("description"));
                    book.setPublishDate(rs.getDate("publish_date"));
                    book.setCoverImage(rs.getString("cover_image"));
                    book.setQuantity(rs.getInt("quantity"));
                    books.add(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    // Lấy tổng số sách
  
    @Override
	public int getTotalBooks() {
        String query = "SELECT COUNT(*) FROM books";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Thêm sách mới
    
    @Override
	public void addBook(BookModel book) {
        String query = "INSERT INTO books (title, isbn, publisher, price, description, publish_date, cover_image, quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, book.getTitle());
            stmt.setObject(2, book.getIsbn());
            stmt.setString(3, book.getPublisher());
            stmt.setBigDecimal(4, book.getPrice());
            stmt.setString(5, book.getDescription());
            stmt.setDate(6, new java.sql.Date(book.getPublishDate().getTime()));
            stmt.setString(7, book.getCoverImage());
            stmt.setInt(8, book.getQuantity());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cập nhật sách
 
    @Override
	public void updateBook(BookModel book) {
        String query = "UPDATE books SET title = ?, isbn = ?, publisher = ?, price = ?, description = ?, publish_date = ?, cover_image = ?, quantity = ? WHERE bookid = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, book.getTitle());
            stmt.setObject(2, book.getIsbn());
            stmt.setString(3, book.getPublisher());
            stmt.setBigDecimal(4, book.getPrice());
            stmt.setString(5, book.getDescription());
            stmt.setDate(6, new java.sql.Date(book.getPublishDate().getTime()));
            stmt.setString(7, book.getCoverImage());
            stmt.setInt(8, book.getQuantity());
            stmt.setInt(9, book.getBookid());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xóa sách
 
    @Override
	public void deleteBook(int bookid) {
        String query = "DELETE FROM books WHERE bookid = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookid);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lấy sách theo ID
 
    @Override
	public BookModel getBookById(int bookid) {
        String query = "SELECT * FROM books WHERE bookid = ?";
        BookModel book = null;

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookid);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    book = new BookModel();
                    book.setBookid(rs.getInt("bookid"));
                    book.setTitle(rs.getString("title"));
                    book.setIsbn(rs.getBigDecimal("isbn"));
                    book.setPublisher(rs.getString("publisher"));
                    book.setPrice(rs.getBigDecimal("price"));
                    book.setDescription(rs.getString("description"));
                    book.setPublishDate(rs.getDate("publish_date"));
                    book.setCoverImage(rs.getString("cover_image"));
                    book.setQuantity(rs.getInt("quantity"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return book;
    }

    // Lấy danh sách đánh giá của sách

    @Override
	public List<RatingModel> getRatingsByBook(int bookid) {
        List<RatingModel> ratings = new ArrayList<>();
        String query = "SELECT r.rating, r.review_text, u.fullname FROM rating r "
                     + "JOIN users u ON r.user_id = u.id "
                     + "WHERE r.book_id = ? ORDER BY r.rating DESC";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookid);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                RatingModel rating = new RatingModel();
                rating.setRating(rs.getInt("rating"));
                rating.setReviewText(rs.getString("review_text"));
                ratings.add(rating);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ratings;
    }
}
