package LTW3.Dao.Impl;


import LTW3.Dao.UserDao;
import LTW3.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;

    // Constructor để khởi tạo EntityManager
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

   
 
	@Override
	public User getUserByEmailAndPassword(String email, String passwd) {
        User user = null;

        try {
            // Sử dụng JPA để truy vấn dữ liệu người dùng từ bảng users
            String query = "SELECT u FROM User u WHERE u.email = :email AND u.passwd = :passwd";
            TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);
            typedQuery.setParameter("email", email);
            typedQuery.setParameter("passwd", passwd);

            // Lấy kết quả
            user = typedQuery.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
	
	@Override
	public User getUserByEmail(String email) {
        // Truy vấn để lấy người dùng qua email
        String query = "SELECT u FROM User u WHERE u.email = :email";
        Query q = entityManager.createQuery(query, User.class);
        q.setParameter("email", email);
        try {
            return (User) q.getSingleResult(); // Trả về một người dùng hoặc null nếu không tìm thấy
        } catch (Exception e) {
            return null; // Trả về null nếu không tìm thấy người dùng
        }
    }


    @Override
	public void addUser(User user) {
        // Lưu người dùng vào cơ sở dữ liệu
        try {
        	entityManager.getTransaction().begin();
        	entityManager.persist(user); // Persist người dùng vào cơ sở dữ liệu
        	entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
            	entityManager.getTransaction().rollback(); // Nếu có lỗi, rollback transaction
            }
            e.printStackTrace();
        }
    }

}
	
	
	