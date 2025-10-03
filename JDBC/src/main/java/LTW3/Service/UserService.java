package LTW3.Service;

import java.util.List;

import LTW3.Model.UserModel;




public interface UserService {

	

	void updateUser(UserModel user);

	void deleteUser(int userId);

	UserModel getUserById(int userId);

	List<UserModel> getUsersPaginated(int start, int recordsPerPage);

	int getTotalUsers();

	UserModel getUserByEmailAndPassword(String email, String passwd);

	boolean isEmailExist(String email);

	boolean addUser(UserModel user);


	

}

