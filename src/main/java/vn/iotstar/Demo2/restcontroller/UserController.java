package vn.iotstar.Demo2.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.iotstar.Demo2.entity.UserInfo;
import vn.iotstar.Demo2.service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/new")
    public String addUser(@RequestBody UserInfo userInfo) {
        return userService.addUser(userInfo);
    }
}