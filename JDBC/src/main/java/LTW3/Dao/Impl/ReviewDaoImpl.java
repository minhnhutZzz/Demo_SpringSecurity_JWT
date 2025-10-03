package LTW3.Dao.Impl;

import LTW3.Dao.ReviewDao;
import LTW3.Model.RatingModel;
import LTW3.Model.ReviewModel;
import LTW3.Util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {

    // Lấy danh sách các đánh giá của một cuốn sách
   
    @Override
	public List<RatingModel> getRatingsByBook(int bookId) {
        List<RatingModel> ratings = new ArrayList<>();
        String query = "SELECT * FROM ratings WHERE book_id = ? ORDER BY rating DESC";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    RatingModel rating = new RatingModel();
                    rating.setRatingId(rs.getInt("rating_id"));
                    rating.setBookId(rs.getInt("book_id"));
                    rating.setUserId(rs.getInt("user_id"));
                    rating.setRating(rs.getInt("rating"));
                    rating.setReviewText(rs.getString("review_text"));
                    ratings.add(rating);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ratings;
    }

    // Thêm một đánh giá vào cơ sở dữ liệu
 
   
	@Override
	public void addRating(RatingModel rating) {
        String query = "INSERT INTO ratings (book_id, user_id, rating, review_text) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, rating.getBookId());
            stmt.setInt(2, rating.getUserId());
            stmt.setInt(3, rating.getRating());
            stmt.setString(4, rating.getReviewText());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   
    @Override
	public boolean addRating(ReviewModel review) {
        String query = "INSERT INTO ratings (book_id, user_id, rating, review_text) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, review.getBookId());
            stmt.setInt(2, review.getUserId());
            stmt.setInt(3, review.getRating());
            stmt.setString(4, review.getReviewText());

            return stmt.executeUpdate() > 0;  // Trả về true nếu thêm thành công
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
