package com.looselycoupled.monoshop.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class JwtLoginResponse {
    
    private String grantType = "Bearer";
    private String accessToken;
    private String refreshToken;
    private String accessTokenExpiresAt;
    private String refreshTokenExpiresAt;
}
