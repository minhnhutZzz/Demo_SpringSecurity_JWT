package LTW3.Dao;

import java.util.List;

import LTW3.Entity.Book;

public interface BookDao {

	Book getBookByIsbn(int isbn);

	Book getBookById(int bookid);

	List<Book> getAllBooks();

	void deleteBook(int bookid);

	void updateBook(Book book);

	void addBook(Book book);

	int getTotalBooks();

	List<Book> getBooksPaginated(int start, int recordsPerPage);

}
