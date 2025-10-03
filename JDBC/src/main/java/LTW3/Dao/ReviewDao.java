package LTW3.Dao;

import java.util.List;

import LTW3.Model.RatingModel;
import LTW3.Model.ReviewModel;



public interface ReviewDao {

	

	List<RatingModel> getRatingsByBook(int bookId);

	boolean addRating(ReviewModel review);

	void addRating(RatingModel rating);




}
