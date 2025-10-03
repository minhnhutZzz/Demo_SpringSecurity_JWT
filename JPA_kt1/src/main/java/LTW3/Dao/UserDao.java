package LTW3.Dao;

import LTW3.Entity.User;

public interface UserDao {

	User getUserByEmailAndPassword(String email, String passwd);

	void addUser(User user);

	User getUserByEmail(String email);

	}

