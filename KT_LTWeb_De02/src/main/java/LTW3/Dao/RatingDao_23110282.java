package LTW3.Dao;

import java.util.List;

import LTW3.Model.RatingModel_23110282;

public interface RatingDao_23110282 {

	List<RatingModel_23110282> getRatingsByBook(int bookId);

	void addRating(RatingModel_23110282 rating);

}
