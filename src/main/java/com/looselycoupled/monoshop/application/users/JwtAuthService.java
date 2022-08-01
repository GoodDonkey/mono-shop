package com.looselycoupled.monoshop.application.users;

public interface JwtAuthService {
    
    JwtLoginResponse login(LoginRequest request);
}
