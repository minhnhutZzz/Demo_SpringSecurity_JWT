package LTW3.Service;

import java.util.List;

import LTW3.Model.RatingModel;
import LTW3.Model.ReviewModel;



public interface ReviewService {

	void addRating(RatingModel rating);

	List<RatingModel> getRatingsByBook(int bookId);

	boolean addReview(ReviewModel review);

	

}
