package com.northcoders.SecurityPlayground.repository;

import com.northcoders.SecurityPlayground.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Update this method to return Optional<User>
    Optional<User> findByGithubUsername(String githubUsername);
}
