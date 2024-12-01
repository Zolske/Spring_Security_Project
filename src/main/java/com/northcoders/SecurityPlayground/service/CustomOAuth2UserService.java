package com.northcoders.SecurityPlayground.service;

import com.northcoders.SecurityPlayground.model.User;
import com.northcoders.SecurityPlayground.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oauth2User = new DefaultOAuth2UserService().loadUser(userRequest);

        // Get the GitHub username and name from the OAuth2 provider
        String githubUsername = oauth2User.getAttribute("login");
        String name = oauth2User.getAttribute("name");

        // Check if user already exists in the database
        User existingUser = userRepository.findByGithubUsername(githubUsername);

        if (existingUser == null) {
            // If not, create a new user and save to the database
            User newUser = new User(name, githubUsername);
            userRepository.save(newUser);
        }

        return oauth2User;
    }
}
