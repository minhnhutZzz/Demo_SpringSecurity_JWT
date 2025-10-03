package LTW3.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import LTW3.Dao.RatingDao_23110282;
import LTW3.Model.RatingModel_23110282;
import LTW3.Util.DBConnect;

public class RatingDaoImpl_23110282 implements RatingDao_23110282 {

  
    @Override
	public void addRating(RatingModel_23110282 rating) {
        String sql = "INSERT INTO rating (bookid, userid, rating, review_text) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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
	public List<RatingModel_23110282> getRatingsByBook(int bookId) {
        List<RatingModel_23110282> ratings = new ArrayList<>();
        String sql = "SELECT * FROM rating WHERE bookid = ? ORDER BY rating DESC";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    RatingModel_23110282 rating = new RatingModel_23110282();
                    rating.setRatingId(rs.getInt("ratingId"));
                    rating.setBookId(rs.getInt("bookid"));
                    rating.setUserId(rs.getInt("userid"));
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
}
