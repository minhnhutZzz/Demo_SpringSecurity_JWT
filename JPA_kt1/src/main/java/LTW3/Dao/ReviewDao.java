package LTW3.Dao;

import java.util.List;

import LTW3.Entity.Book;
import LTW3.Entity.Rating;

public interface ReviewDao {

	void addRating(Rating rating);

	List<Rating> getRatingsByBook(Book book);

}
