package com.northcoders.SecurityPlayground.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/*")
                .authorizeHttpRequests(auth ->
                auth
                        .requestMatchers(HttpMethod.GET, "/open/greeting").permitAll()
                        .requestMatchers("/protected/greeting").authenticated()
                );

        return http.build();
    }
}
