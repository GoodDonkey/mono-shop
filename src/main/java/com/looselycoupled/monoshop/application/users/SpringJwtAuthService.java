package com.looselycoupled.monoshop.application.users;

import com.looselycoupled.monoshop.application.users.jwt.CreateAccessToken;
import com.looselycoupled.monoshop.application.users.jwt.CreateRefreshToken;
import com.looselycoupled.monoshop.application.users.jwt.JwtManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
class SpringJwtAuthService implements JwtAuthService{
    
    private final JwtManager jwtManager;
    
    @Override
    public JwtLoginResponse login(LoginRequest request) {
        String username = request.getUsername();
        String accessToken = jwtManager.createAccessToken(new CreateAccessToken(username));
        String refreshToken = jwtManager.createRefreshToken(new CreateRefreshToken(username));
        
        return JwtLoginResponse.builder()
                       .accessToken(accessToken)
                       .refreshToken(refreshToken)
                       .accessTokenExpiresAt(jwtManager.getExpireTimeFromToken(accessToken))
                       .refreshTokenExpiresAt(jwtManager.getExpireTimeFromToken(refreshToken))
                       .build();
    }
}
