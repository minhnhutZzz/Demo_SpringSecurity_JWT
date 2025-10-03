package LTW3.Service.Impl;

import LTW3.Dao.UserDao;
import LTW3.Model.UserModel;
import LTW3.Service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    // Constructor để khởi tạo UserDao
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

  
    @Override
	public int getTotalUsers() {
        return userDao.getTotalUsers();  // Lấy tổng số người dùng từ UserDao
    }

   
    @Override
	public List<UserModel> getUsersPaginated(int start, int recordsPerPage) {
        return userDao.getUsersPaginated(start, recordsPerPage);  // Lấy danh sách người dùng với phân trang
    }

    
    @Override
	public UserModel getUserById(int userId) {
        return userDao.getUserById(userId);  // Lấy người dùng theo ID từ UserDao
    }

 
    @Override
	public void deleteUser(int userId) {
        userDao.deleteUser(userId);  // Xóa người dùng từ UserDao
    }

   
    @Override
	public void updateUser(UserModel user) {
        userDao.updateUser(user);  // Cập nhật người dùng trong UserDao
    }

   
  
    @Override
	public boolean addUser(UserModel user) {
        return userDao.addUser(user);  // Thêm người dùng mới vào cơ sở dữ liệu và trả về kết quả
    }
    
  
    @Override
	public UserModel getUserByEmailAndPassword(String email, String passwd) {
        return userDao.getUserByEmailAndPassword(email, passwd);  // Gọi từ UserDao
    }
    
    
    @Override
	public boolean isEmailExist(String email) {
        return userDao.isEmailExist(email);  // Kiểm tra email tồn tại từ UserDao
    }
}
