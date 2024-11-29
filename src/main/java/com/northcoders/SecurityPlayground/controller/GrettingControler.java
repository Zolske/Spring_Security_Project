package com.northcoders.SecurityPlayground.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GrettingControler {

    @GetMapping("/open/greeting")
    public static String openGreeting(){
        return "Welcome to our API.";
    }

    @GetMapping("/protected/greeting")
    public static String protectedGreeting(@AuthenticationPrincipal OAuth2User principal) {
        String name = principal.getAttribute("name");
        String username = principal.getAttribute("login");
//        return "Hello my API friend.";
        return ("Hello, " + name + ". Your username is: " + username + ".");
    }
}
