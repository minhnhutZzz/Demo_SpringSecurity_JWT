package LTW3.Service.Impl;

import java.util.List;

import LTW3.Dao.AuthorDao;
import LTW3.Model.AuthorModel;
import LTW3.Service.AuthorService;


public class AuthorServiceImpl implements AuthorService {

    private AuthorDao authorDao;

    // Constructor để khởi tạo AuthorDao
    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

   
    @Override
	public AuthorModel getAuthorById(int authorId) {
        return authorDao.getAuthorById(authorId);  // Gọi phương thức của DAO để lấy tác giả theo ID
    }


    @Override
	public void deleteAuthor(int authorId) {
        authorDao.deleteAuthor(authorId);  // Gọi phương thức của DAO để xóa tác giả
    }

   
    @Override
	public void updateAuthor(AuthorModel author) {
        authorDao.updateAuthor(author);  // Gọi phương thức của DAO để cập nhật thông tin tác giả
    }

    @Override
	public void addAuthor(AuthorModel author) {
        authorDao.addAuthor(author);  // Gọi phương thức của DAO để thêm tác giả mới
    }

    
    @Override
	public int getTotalAuthors() {
        return authorDao.getTotalAuthors();  // Gọi phương thức của DAO để lấy tổng số tác giả
    }

    
    @Override
	public List<AuthorModel> getAuthorsPaginated(int start, int recordsPerPage) {
        return authorDao.getAuthorsPaginated(start, recordsPerPage);  // Gọi phương thức của DAO để lấy tác giả phân trang
    }

   
    @Override
	public List<AuthorModel> getAllAuthors() {
        return authorDao.getAllAuthors();  // Gọi phương thức của DAO để lấy tất cả tác giả
    }
}
