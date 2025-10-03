package LTW3.Dao.Impl;
 
import LTW3.Dao.AuthorDao;
import LTW3.Model.AuthorModel;
import LTW3.Util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {

    // Lấy tất cả tác giả
  
    @Override
	public List<AuthorModel> getAllAuthors() {
        List<AuthorModel> authors = new ArrayList<>();
        String query = "SELECT * FROM author";  // SQL lấy tất cả tác giả

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            // Duyệt qua các kết quả và thêm vào danh sách tác giả
            while (rs.next()) {
                AuthorModel author = new AuthorModel();
                author.setAuthorId(rs.getInt("author_id"));
                author.setAuthorName(rs.getString("author_name"));
                author.setDateOfBirth(rs.getDate("date_of_birth"));
                authors.add(author);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return authors;
    }

    // Lấy tác giả với phân trang
    @Override
	public List<AuthorModel> getAuthorsPaginated(int start, int recordsPerPage) {
        List<AuthorModel> authors = new ArrayList<>();
        String query = "SELECT * FROM author ORDER BY author_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";  // Sử dụng OFFSET và FETCH NEXT cho SQL Server

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, start);  // Vị trí bắt đầu (OFFSET)
            stmt.setInt(2, recordsPerPage);  // Số lượng tác giả mỗi trang (FETCH NEXT)

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    AuthorModel author = new AuthorModel();
                    author.setAuthorId(rs.getInt("author_id"));
                    author.setAuthorName(rs.getString("author_name"));
                    author.setDateOfBirth(rs.getDate("date_of_birth"));
                    authors.add(author);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();  // Xử lý lỗi nếu có
        }

        return authors;
    }

    // Lấy tổng số tác giả (dùng để tính toán phân trang)
   
    @Override
	public int getTotalAuthors() {
        String query = "SELECT COUNT(*) FROM author";
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

    // Thêm tác giả mới
    
    @Override
	public void addAuthor(AuthorModel author) {
        String query = "INSERT INTO author (author_name, date_of_birth) VALUES (?, ?)";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, author.getAuthorName());
            stmt.setDate(2, new java.sql.Date(author.getDateOfBirth().getTime()));  // Định dạng lại ngày
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cập nhật thông tin tác giả
    
    @Override
	public void updateAuthor(AuthorModel author) {
        String query = "UPDATE author SET author_name = ?, date_of_birth = ? WHERE author_id = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, author.getAuthorName());
            stmt.setDate(2, new java.sql.Date(author.getDateOfBirth().getTime()));  // Định dạng lại ngày
            stmt.setInt(3, author.getAuthorId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xóa tác giả
    
    @Override
	public void deleteAuthor(int authorId) {
        String query = "DELETE FROM author WHERE author_id = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, authorId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lấy tác giả theo ID
   
    @Override
	public AuthorModel getAuthorById(int authorId) {
        String query = "SELECT * FROM author WHERE author_id = ?";
        AuthorModel author = null;

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, authorId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    author = new AuthorModel();
                    author.setAuthorId(rs.getInt("author_id"));
                    author.setAuthorName(rs.getString("author_name"));
                    author.setDateOfBirth(rs.getDate("date_of_birth"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return author;
    }
}
