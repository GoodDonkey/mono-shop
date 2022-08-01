package com.looselycoupled.monoshop.application.users.features.jwt;

public interface JwtManager {
    String createAccessToken(CreateAccessToken request);
    String createRefreshToken(CreateRefreshToken request);
    
    String getExpireTimeFromToken(String token);
    
    String resolveUsername(String token);
    
    boolean validateToken(String token);
}
