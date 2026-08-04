package com.interviewace.backend.controller;

import com.interviewace.backend.dto.LoginRequest;
import com.interviewace.backend.dto.LoginResponse;
import com.interviewace.backend.dto.RegisterRequest;
import com.interviewace.backend.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class
AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}