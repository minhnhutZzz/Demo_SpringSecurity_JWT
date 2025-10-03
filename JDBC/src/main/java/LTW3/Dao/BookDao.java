package LTW3.Dao;

import java.util.List;

import LTW3.Model.BookModel;
import LTW3.Model.RatingModel;



public interface BookDao {

	List<RatingModel> getRatingsByBook(int bookid);

	BookModel getBookById(int bookid);

	void deleteBook(int bookid);

	void updateBook(BookModel book);

	void addBook(BookModel book);

	int getTotalBooks();

	List<BookModel> getBooksPaginated(int start, int recordsPerPage);

	List<BookModel> getAllBooks();

	

	
}
