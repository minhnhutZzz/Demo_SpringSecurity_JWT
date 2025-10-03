package LTW3.Dao;

import java.util.List;

import LTW3.Model.UserModel;



public interface UserDao {

	int getTotalUsers();

	List<UserModel> getUsersPaginated(int start, int recordsPerPage);

	UserModel getUserById(int userId);

	void deleteUser(int userId);

	void updateUser(UserModel user);


	UserModel getUserByEmailAndPassword(String email, String passwd);

	boolean isEmailExist(String email);

	boolean addUser(UserModel user);




	}

