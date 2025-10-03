package LTW3.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ratingid;

    @ManyToOne
    @JoinColumn(name = "bookid")
    private Book book;  // Liên kết với Book

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;  // Liên kết với User

    @Column(name = "rating")
    private int rating;  // Điểm đánh giá (ví dụ: 1-5)

    @Column(name = "review_text")
    private String reviewText;  // Nội dung đánh giá

    // Getter và Setter methods
    public int getRatingid() {
        return ratingid;
    }

    public void setRatingid(int ratingid) {
        this.ratingid = ratingid;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
