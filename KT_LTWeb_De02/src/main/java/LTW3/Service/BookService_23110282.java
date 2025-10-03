package LTW3.Service;

import java.util.List;

import LTW3.Model.BookModel_23110282;
import LTW3.Model.RatingModel_23110282;



public interface BookService_23110282 {

	List<BookModel_23110282> getAllBooks();

	List<BookModel_23110282> getBooksPaginated(int start, int recordsPerPage);

	int getTotalBooks();

	void addBook(BookModel_23110282 book);

	void updateBook(BookModel_23110282 book);

	void deleteBook(int bookId);

	BookModel_23110282 getBookById(int bookId);

	List<RatingModel_23110282> getRatingsByBook(int bookId);

	

}
