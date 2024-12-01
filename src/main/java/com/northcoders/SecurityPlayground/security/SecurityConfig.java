package com.northcoders.SecurityPlayground.security;

import com.northcoders.SecurityPlayground.service.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF and allow frames for H2 console
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions().disable())

                // Authorization rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/v1/open/greeting").permitAll() // Open endpoint
                        .requestMatchers(HttpMethod.GET, "/api/v1/protected/greeting").authenticated() // Protected endpoint
                        .requestMatchers("/h2-console/**").permitAll() // Allow H2 console access
                        .anyRequest().authenticated() // Other requests require authentication
                )

                // OAuth2 login configuration with custom user service
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)))
                .oauth2Client(withDefaults());

        return http.build();
    }
}
