package LTW3.Dao;

import java.util.List;

import LTW3.Model.UserModel_23110282;



public interface UserDao_23110282 {

	int getTotalUsers();

	List<UserModel_23110282> getUsersPaginated(int start, int recordsPerPage);

	UserModel_23110282 getUserById(int userId);

	void deleteUser(int userId);

	void updateUser(UserModel_23110282 user);


	UserModel_23110282 getUserByEmailAndPassword(String email, String passwd);

	boolean isEmailExist(String email);

	boolean addUser(UserModel_23110282 user);




	}

