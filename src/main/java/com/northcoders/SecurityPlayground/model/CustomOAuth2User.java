package com.northcoders.SecurityPlayground.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private OAuth2User oauth2User;
    private HashSet<SimpleGrantedAuthority> authorities;

    // Constructor
    public CustomOAuth2User(OAuth2User oauth2User, HashSet<SimpleGrantedAuthority> authorities) {
        this.oauth2User = oauth2User;
        this.authorities = authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        // Return the attributes from the original OAuth2User
        return oauth2User.getAttributes();
    }

    @Override
    public String getAttribute(String name) {
        // Return the specific attribute from the original OAuth2User
        return oauth2User.getAttribute(name);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return the authorities (roles/permissions) for the user
        return authorities;
    }

    @Override
    public String getName() {
        // Return the name of the user (can be the GitHub username or actual name)
        return oauth2User.getName();
    }

}
