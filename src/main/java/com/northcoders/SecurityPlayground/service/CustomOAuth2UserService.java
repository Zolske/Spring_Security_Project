package com.northcoders.SecurityPlayground.service;

import com.northcoders.SecurityPlayground.model.CustomOAuth2User;
import com.northcoders.SecurityPlayground.model.User;
import com.northcoders.SecurityPlayground.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.HashSet;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest); // Use the default OAuth2UserService to get the user info

        String name = oAuth2User.getAttribute("name"); // Get the user's name
        String githubLogin = oAuth2User.getAttribute("login"); // Get the user's GitHub login

        // Check if the user exists in the database or save the new user
        User user = userRepository.findByGithubUsername(githubLogin)
                .orElseGet(() -> userRepository.save(new User(name, githubLogin)));

        // Assign roles based on the GitHub login (arbitrary role assignment for demo purposes)
        HashSet<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER")); // Default user role

        // Assign admin role based on a specific GitHub login (this is just for demo purposes)
        if ("beikmanv".equals(githubLogin)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            System.out.println("Admin role assigned to: " + githubLogin);
        }

        // Return the custom OAuth2User with the loaded authorities
        return new CustomOAuth2User(oAuth2User, authorities);
    }
}
