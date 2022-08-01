package com.looselycoupled.monoshop.application.users.features.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
class SpringJwtManager implements JwtManager {
    
    private static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS256;
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 30 * 60 * 1000L;   // 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 7 * 60 * 60 * 24 * 1000L; // 7일
    
    private final String secretKeyString = "temp";
    private final Key secretKey;
    
    public SpringJwtManager() {
        // 키 스트링 주입받을 예정
        byte[] keyBytes = Base64.getDecoder().decode(secretKeyString);
        this.secretKey = new SecretKeySpec(keyBytes, ALGORITHM.getJcaName());
    }
    
    @Override
    public String createAccessToken(CreateAccessToken request){
        return Jwts.builder()
                       .setSubject(request.getUsername())
                       .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))
                       .signWith(ALGORITHM, secretKey)
                   .compact();
    }
    
    @Override
    public String createRefreshToken(CreateRefreshToken request){
        return Jwts.builder()
                       .setSubject(request.getUsername())
                       .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_TIME))
                       .signWith(ALGORITHM, secretKey)
                   .compact();
    }
    
    @Override
    public String getExpireTimeFromToken(String token) {
        return Jwts.parser()
                       .setSigningKey(secretKey)
                   .parseClaimsJws(token)
                   .getBody()
                   .getExpiration()
                   .toString();
    }
}
