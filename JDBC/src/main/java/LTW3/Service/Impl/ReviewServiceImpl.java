package LTW3.Service.Impl;

import LTW3.Dao.ReviewDao;
import LTW3.Model.RatingModel;
import LTW3.Model.ReviewModel;
import LTW3.Service.ReviewService;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {

    private ReviewDao reviewDao;

    // Constructor để khởi tạo ReviewDao
    public ReviewServiceImpl(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

  
    @Override
	public List<RatingModel> getRatingsByBook(int bookId) {
        return reviewDao.getRatingsByBook(bookId);  // Lấy danh sách đánh giá của sách từ ReviewDao
    }

   
    @Override
	public void addRating(RatingModel rating) {
        reviewDao.addRating(rating);  // Thêm đánh giá mới vào cơ sở dữ liệu
    }
    
  
    @Override
	public boolean addReview(ReviewModel review) {
        return reviewDao.addRating(review);  // Thêm review vào cơ sở dữ liệu
    }
}
