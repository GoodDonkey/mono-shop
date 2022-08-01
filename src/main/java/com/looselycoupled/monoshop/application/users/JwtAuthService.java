package com.looselycoupled.monoshop.application.users;

public interface JwtAuthService {
    
    SignupResponse signup(SignupRequest request);
    JwtLoginResponse login(LoginRequest request);
}
