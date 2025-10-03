package LTW3.Service;

import java.util.List;

import LTW3.Model.RatingModel_23110282;

public interface RatingService_23110282 {

	List<RatingModel_23110282> getRatingsByBook(int bookId);

	boolean addRating(RatingModel_23110282 rating);

}
