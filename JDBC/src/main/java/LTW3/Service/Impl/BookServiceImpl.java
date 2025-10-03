package LTW3.Service.Impl;

import LTW3.Dao.BookDao;
import LTW3.Model.BookModel;
import LTW3.Model.RatingModel;
import LTW3.Service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    // Constructor để khởi tạo BookDao
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

 
    @Override
	public List<RatingModel> getRatingsByBook(int bookId) {
        return bookDao.getRatingsByBook(bookId);  // Lấy danh sách đánh giá theo sách
    }

  
    @Override
	public BookModel getBookById(int bookId) {
        return bookDao.getBookById(bookId);  // Lấy thông tin sách theo ID
    }

    
    @Override
	public void deleteBook(int bookId) {
        bookDao.deleteBook(bookId);  // Xóa sách theo ID
    }

  
    @Override
	public void updateBook(BookModel book) {
        bookDao.updateBook(book);  // Cập nhật thông tin sách
    }

    
    @Override
	public void addBook(BookModel book) {
        bookDao.addBook(book);  // Thêm sách mới vào cơ sở dữ liệu
    }

   
    @Override
	public int getTotalBooks() {
        return bookDao.getTotalBooks();  // Lấy tổng số sách để tính số trang
    }

  
    @Override
	public List<BookModel> getBooksPaginated(int start, int recordsPerPage) {
        return bookDao.getBooksPaginated(start, recordsPerPage);  // Lấy sách phân trang
    }

    
    @Override
	public List<BookModel> getAllBooks() {
        return bookDao.getAllBooks();  // Lấy tất cả sách
    }
}
