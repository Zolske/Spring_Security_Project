package com.northcoders.SecurityPlayground.repository;

import com.northcoders.SecurityPlayground.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // Custom query to find a user by their GitHub username
    User findByGithubUsername(String githubUsername);
}
