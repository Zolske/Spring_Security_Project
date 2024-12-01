package com.northcoders.SecurityPlayground.controller;

import com.northcoders.SecurityPlayground.model.User;
import com.northcoders.SecurityPlayground.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/addUser")
    public String addUser() {
        // Add a test user
        User user = new User("testuser", "testuser123");
        userRepository.save(user);
        return "User added with username: " + user.getGithubUsername();
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        // Retrieve all users
        return userRepository.findAll();
    }
}
