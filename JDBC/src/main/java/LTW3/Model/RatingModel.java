package LTW3.Model;

public class RatingModel {
    private int ratingId;
    private int bookId;
    private int userId;
    private int rating;
    private String reviewText;
    private UserModel user;  // Thêm thuộc tính UserModel

    // Getter và Setter cho tất cả các thuộc tính
    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public UserModel getUser() {
        return user;  // Getter cho user
    }

    public void setUser(UserModel user) {
        this.user = user;  // Setter cho user
    }
}
