package LTW3.Service.Impl;

import LTW3.Dao.BookDao_23110282;
import LTW3.Model.BookModel_23110282;
import LTW3.Model.RatingModel_23110282;
import LTW3.Service.BookService_23110282;

import java.util.List;

public class BookServiceImpl_23110282 implements BookService_23110282 {

    private BookDao_23110282 bookDao;

    // Constructor để khởi tạo BookDao
    public BookServiceImpl_23110282(BookDao_23110282 bookDao) {
        this.bookDao = bookDao;
    }

 
    @Override
	public List<RatingModel_23110282> getRatingsByBook(int bookId) {
        return bookDao.getRatingsByBook(bookId);  // Lấy danh sách đánh giá theo sách
    }

  
    @Override
	public BookModel_23110282 getBookById(int bookId) {
        return bookDao.getBookById(bookId);  // Lấy thông tin sách theo ID
    }

    
    @Override
	public void deleteBook(int bookId) {
        bookDao.deleteBook(bookId);  // Xóa sách theo ID
    }

  
    @Override
	public void updateBook(BookModel_23110282 book) {
        bookDao.updateBook(book);  // Cập nhật thông tin sách
    }

    
    @Override
	public void addBook(BookModel_23110282 book) {
        bookDao.addBook(book);  // Thêm sách mới vào cơ sở dữ liệu
    }

   
    @Override
	public int getTotalBooks() {
        return bookDao.getTotalBooks();  // Lấy tổng số sách để tính số trang
    }

  
    @Override
	public List<BookModel_23110282> getBooksPaginated(int start, int recordsPerPage) {
        return bookDao.getBooksPaginated(start, recordsPerPage);  // Lấy sách phân trang
    }

    
    @Override
	public List<BookModel_23110282> getAllBooks() {
        return bookDao.getAllBooks();  // Lấy tất cả sách
    }
}
