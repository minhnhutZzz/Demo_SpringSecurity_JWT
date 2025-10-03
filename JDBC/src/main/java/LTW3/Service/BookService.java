package LTW3.Service;

import java.util.List;

import LTW3.Model.BookModel;
import LTW3.Model.RatingModel;



public interface BookService {

	List<BookModel> getAllBooks();

	List<BookModel> getBooksPaginated(int start, int recordsPerPage);

	int getTotalBooks();

	void addBook(BookModel book);

	void updateBook(BookModel book);

	void deleteBook(int bookId);

	BookModel getBookById(int bookId);

	List<RatingModel> getRatingsByBook(int bookId);

	

}
