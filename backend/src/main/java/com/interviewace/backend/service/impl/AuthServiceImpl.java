package com.interviewace.backend.service.impl;

import com.interviewace.backend.dto.LoginRequest;
import com.interviewace.backend.dto.LoginResponse;
import com.interviewace.backend.security.JwtService;
import com.interviewace.backend.dto.RegisterRequest;
import com.interviewace.backend.entity.Role;
import com.interviewace.backend.entity.User;
import com.interviewace.backend.repository.UserRepository;
import com.interviewace.backend.service.AuthService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public String register(RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){
            return "Email already exists";
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);

        return "Registration Successful";

    }
    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        System.out.println("Entered Password: " + request.getPassword());
        System.out.println("Stored Hash: " + user.getPassword());

        boolean match = passwordEncoder.matches(request.getPassword(), user.getPassword());
        System.out.println("Password Match: " + match);

        if (!match) {
            throw new RuntimeException("Invalid Password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponse(token);
    }
}