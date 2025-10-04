package vn.iotstar.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.User;
import vn.iotstar.model.LoginResponse;
import vn.iotstar.model.LoginUserModel;
import vn.iotstar.model.RegisterUserModel;
import vn.iotstar.repository.UserRepository;


@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public User signup(RegisterUserModel input) {
        User user = new User();
        user.setEmail(input.getEmail());
        user.setName(input.getFullName()); // Gán fullName vào cột name
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setImages("default-image.jpg"); // Gán giá trị mặc định cho images
        return userRepository.save(user);
    }

    public User authenticate(LoginUserModel input) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword())
        );
        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }

    public LoginResponse createResponse(User user) {
        String jwtToken = jwtService.generateToken(user);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return loginResponse;
    }
}
