package LTW3.Service;

import java.util.List;

import LTW3.Model.UserModel_23110282;




public interface UserService_23110282 {

	

	void updateUser(UserModel_23110282 user);

	void deleteUser(int userId);

	UserModel_23110282 getUserById(int userId);

	List<UserModel_23110282> getUsersPaginated(int start, int recordsPerPage);

	int getTotalUsers();

	UserModel_23110282 getUserByEmailAndPassword(String email, String passwd);

	boolean isEmailExist(String email);

	boolean addUser(UserModel_23110282 user);


	

}

