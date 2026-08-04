package com.interviewace.backend.controller;

import com.interviewace.backend.dto.UserResponse;
import com.interviewace.backend.security.JwtService;
import com.interviewace.backend.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("/profile")
    public UserResponse getProfile(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);

        String email = jwtService.extractEmail(token);

        return userService.getCurrentUser(email);
    }
}