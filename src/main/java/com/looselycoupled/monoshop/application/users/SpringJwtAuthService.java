package com.looselycoupled.monoshop.application.users;

import com.looselycoupled.monoshop.application.users.data.Role;
import com.looselycoupled.monoshop.application.users.data.UserInfo;
import com.looselycoupled.monoshop.application.users.data.UserInfoJpaRepository;
import com.looselycoupled.monoshop.application.users.features.jwt.CreateAccessToken;
import com.looselycoupled.monoshop.application.users.features.jwt.CreateRefreshToken;
import com.looselycoupled.monoshop.application.users.features.jwt.JwtManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
class SpringJwtAuthService implements JwtAuthService{
    
    private final JwtManager jwtManager;
    private final PasswordEncoder passwordEncoder;
    private final UserInfoJpaRepository userInfoJpaRepository;
    
    @Override
    @Transactional
    public SignupResponse signup(SignupRequest request) {
        log.debug("SignupRequest: {}", request);
        String username = request.getUsername();
        String password = passwordEncoder.encode(request.getPassword());
        UserInfo userInfo = userInfoJpaRepository.findByUsername(username);
        if (userInfo != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        UserInfo newUserInfo = UserInfo.builder().username(username).password(password).role(Role.ROLE_MEMBER).build();
        UserInfo save = userInfoJpaRepository.save(newUserInfo);
        log.debug("UserInfo: {}", save);
        return new SignupResponse(username);
    }
    
    @Override
    public JwtLoginResponse login(LoginRequest request) {
        log.debug("LoginRequest: {}", request);
        String username = request.getUsername();
        UserInfo userInfo = userInfoJpaRepository.findByUsername(username);
        if (userInfo == null) {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
        if (validatePassword(request, userInfo)){
            log.debug("userInfo: {}", userInfo.getPassword());
            log.debug("request: {}", passwordEncoder.encode(request.getPassword()));
            throw new IllegalStateException("패스워드가 불일치 합니다.");
        }
        String accessToken = jwtManager.createAccessToken(new CreateAccessToken(username));
        String refreshToken = jwtManager.createRefreshToken(new CreateRefreshToken(username));
        log.debug("login successful: {}", accessToken);
        return JwtLoginResponse.builder()
                       .accessToken(accessToken)
                       .refreshToken(refreshToken)
                       .accessTokenExpiresAt(jwtManager.getExpireTimeFromToken(accessToken))
                       .refreshTokenExpiresAt(jwtManager.getExpireTimeFromToken(refreshToken))
                       .build();
    }
    
    private boolean validatePassword(LoginRequest request, UserInfo userInfo) {
        return !passwordEncoder.matches(request.getPassword(), userInfo.getPassword());
    }
}
