package LTW3.Dao;

import java.util.List;

import LTW3.Model.BookModel_23110282;
import LTW3.Model.RatingModel_23110282;



public interface BookDao_23110282 {

	List<RatingModel_23110282> getRatingsByBook(int bookid);

	BookModel_23110282 getBookById(int bookid);

	void deleteBook(int bookid);

	void updateBook(BookModel_23110282 book);

	void addBook(BookModel_23110282 book);

	int getTotalBooks();

	List<BookModel_23110282> getBooksPaginated(int start, int recordsPerPage);

	List<BookModel_23110282> getAllBooks();

	

	
}
