package com.northcoders.SecurityPlayground.security;

import com.northcoders.SecurityPlayground.service.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Authorization rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/v1/open/greeting").permitAll() // Open endpoint
                        .requestMatchers(HttpMethod.GET, "/api/v1/protected/greeting").authenticated() // Protected endpoint
                        .requestMatchers("/h2-console/**").permitAll() // Allow H2 console access
                        .anyRequest().authenticated()
                )
                // OAuth2 login
                .oauth2Login(oauth -> oauth
                        .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService)) // Use custom OAuth2 user service
                )
                // Disable CSRF and Frame Options for H2 console
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity with H2
                .headers(headers -> headers.frameOptions().disable()); // Allow frames for H2 console

        return http.build();
    }
}
