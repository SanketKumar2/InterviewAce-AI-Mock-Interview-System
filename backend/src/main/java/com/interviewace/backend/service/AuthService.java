package com.interviewace.backend.service;

import com.interviewace.backend.dto.LoginRequest;
import com.interviewace.backend.dto.LoginResponse;
import com.interviewace.backend.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

}