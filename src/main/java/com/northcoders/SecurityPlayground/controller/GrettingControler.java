package com.northcoders.SecurityPlayground.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GrettingControler {

    @GetMapping("open/greeting")
    public static String openGreeting(){
        return "Welcome to our API.";
    }

    @GetMapping("protected/greeting")
    public static String protectedGreeting(){
        return "Hello my API friend. :)";
    }
}
