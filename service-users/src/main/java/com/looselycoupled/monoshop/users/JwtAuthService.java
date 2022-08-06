package com.looselycoupled.monoshop.users;

public interface JwtAuthService {
    
    SignupResponse signup(SignupRequest request);
    JwtLoginResponse login(LoginRequest request);
}
