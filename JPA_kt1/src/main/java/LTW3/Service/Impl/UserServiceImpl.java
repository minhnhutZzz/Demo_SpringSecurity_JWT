package LTW3.Service.Impl;


import LTW3.Dao.UserDao;
import LTW3.Dao.Impl.UserDaoImpl;
import LTW3.Entity.User;
import LTW3.Service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UserServiceImpl implements UserService {

    private EntityManagerFactory entityManagerFactory;
    
    public UserServiceImpl() {
        // Tạo EntityManagerFactory để kết nối với cơ sở dữ liệu
        entityManagerFactory = Persistence.createEntityManagerFactory("dataSource");
    }

    // Đăng nhập người dùng
   

	@Override
	public User login(String email, String passwd) {
        // Khởi tạo EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        // Sử dụng UserDAOImpl để truy vấn thông tin người dùng
        UserDao userDAO = new UserDaoImpl(entityManager);

        // Lấy thông tin người dùng từ cơ sở dữ liệu
        User user = userDAO.getUserByEmailAndPassword(email, passwd);
        
        entityManager.close(); // Đảm bảo đóng EntityManager sau khi sử dụng
        return user;
    }
}
