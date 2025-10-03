package LTW3.Dao.Impl;
 
import LTW3.Dao.UserDao_23110282;
import LTW3.Model.UserModel_23110282;
import LTW3.Util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl_23110282 implements UserDao_23110282 {

    // Thêm người dùng mới
	
	
	
    @Override
	public boolean isEmailExist(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            return rs.next();  // Nếu có kết quả trả về, email đã tồn tại
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

 
    
	
	
    @Override
	public UserModel_23110282 getUserByEmailAndPassword(String email, String passwd) {
        UserModel_23110282 user = null;
        String query = "SELECT * FROM users WHERE email = ? AND passwd = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, passwd);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new UserModel_23110282();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setFullname(rs.getString("fullname"));
                user.setIsAdmin(rs.getBoolean("is_admin"));
                user.setPasswd(rs.getString("passwd"));
                user.setSignupDate(rs.getDate("signup_date"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
    
    
    @Override
	public boolean addUser(UserModel_23110282 user) {
        String query = "INSERT INTO users (email, fullname, passwd, is_admin) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getFullname());
            stmt.setString(3, user.getPasswd());  // Mã hóa mật khẩu trước khi lưu
            stmt.setBoolean(4, user.isIsAdmin());

            return stmt.executeUpdate() > 0;  // Trả về true nếu thêm thành công
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật thông tin người dùng
    
    @Override
	public void updateUser(UserModel_23110282 user) {
        String query = "UPDATE users SET email = ?, fullname = ?, passwd = ?, signup_date = ?, last_login = ?, is_admin = ? WHERE id = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getFullname());
            stmt.setString(3, user.getPasswd());
            stmt.setDate(4, new java.sql.Date(user.getSignupDate().getTime()));  // Định dạng lại ngày
            stmt.setDate(5, new java.sql.Date(user.getLastLogin().getTime()));  // Định dạng lại ngày
            stmt.setBoolean(6, user.isIsAdmin());
            stmt.setInt(7, user.getId());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Xóa người dùng
  @Override
public void deleteUser(int userId) {
        String query = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lấy người dùng theo ID
    
    @Override
	public UserModel_23110282 getUserById(int userId) {
        String query = "SELECT * FROM users WHERE id = ?";
        UserModel_23110282 user = null;

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new UserModel_23110282();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setFullname(rs.getString("fullname"));
                user.setPasswd(rs.getString("passwd"));
                user.setSignupDate(rs.getTimestamp("signup_date"));
                user.setLastLogin(rs.getTimestamp("last_login"));
                user.setIsAdmin(rs.getBoolean("is_admin"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    // Lấy danh sách người dùng với phân trang
   
    @Override
	public List<UserModel_23110282> getUsersPaginated(int start, int recordsPerPage) {
        List<UserModel_23110282> users = new ArrayList<>();
        String query = "SELECT * FROM users ORDER BY id LIMIT ? OFFSET ?";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, recordsPerPage);  // Số lượng người dùng mỗi trang
            stmt.setInt(2, start);  // Vị trí bắt đầu (OFFSET)

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    UserModel_23110282 user = new UserModel_23110282();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setFullname(rs.getString("fullname"));
                    user.setPasswd(rs.getString("passwd"));
                    user.setSignupDate(rs.getTimestamp("signup_date"));
                    user.setLastLogin(rs.getTimestamp("last_login"));
                    user.setIsAdmin(rs.getBoolean("is_admin"));
                    users.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    // Lấy tổng số người dùng để tính toán số trang
    
    @Override
	public int getTotalUsers() {
        int totalRecords = 0;
        String query = "SELECT COUNT(*) FROM users";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalRecords = rs.getInt(1);  // Lấy kết quả tổng số dòng
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalRecords;
    }
}
