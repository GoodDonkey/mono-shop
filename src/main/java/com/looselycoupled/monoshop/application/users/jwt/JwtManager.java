package com.looselycoupled.monoshop.application.users.jwt;

public interface JwtManager {
    String createAccessToken(CreateAccessToken request);
    String createRefreshToken(CreateRefreshToken request);
    
    String getExpireTimeFromToken(String token);
}
