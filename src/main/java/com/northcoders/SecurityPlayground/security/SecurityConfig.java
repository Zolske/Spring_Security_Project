package com.northcoders.SecurityPlayground.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

// Client ID: Ov23lim4zgH9KgJCCeRu
// Client secret: ee78fc952073de43af185903a1449f4f8e525cef

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .securityMatcher("/api/*")
                .authorizeHttpRequests(auth ->
                auth
                        .requestMatchers(HttpMethod.GET, "/api/v1/open/greeting").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/v1/protected/greeting").authenticated())
                        .oauth2Login(withDefaults());

        return http.build();
    }
}
