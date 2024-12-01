package com.northcoders.SecurityPlayground.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AdminController {

    @GetMapping("/protected/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Welcome Admin!";
    }

    @GetMapping("/check-authorities")
    public String checkAuthorities() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authorities: " + authentication.getAuthorities());
        return "Check your console for authorities.";
    }

}
