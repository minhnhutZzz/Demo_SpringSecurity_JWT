package vn.iotstar.Demo3.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
	UserDetails loadUserByUsername(String username);

}
