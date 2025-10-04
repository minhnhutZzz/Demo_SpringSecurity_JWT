package vn.iotstar.service;


import org.springframework.stereotype.Service;
import vn.iotstar.entity.User;
import vn.iotstar.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    // Constructor injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Lấy tất cả người dùng
    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add); 
        return users;
    }
}

