package com.looselycoupled.monoshop.web;

import com.looselycoupled.monoshop.application.users.*;
import com.looselycoupled.monoshop.web.dtos.SuccessResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {
    
    private final JwtAuthService jwtAuthService;
    
    @PostMapping("/api/v1/signup")
    public ResponseEntity<SuccessResponseBody> signup(@Valid @RequestBody SignupRequest request) {
        log.debug("SignupRequest: {}", request);
        SignupResponse signupResponse = jwtAuthService.signup(request);
        return ResponseEntity.ok().body(SuccessResponseBody.builder()
                                                           .status(HttpStatus.OK.value())
                                                           .message("회원가입에 성공하였습니다.")
                                                           .data(signupResponse)
                                                           .build());
    }
    
    @PostMapping("/api/v1/login")
    public ResponseEntity<SuccessResponseBody> login(@Valid @RequestBody LoginRequest request) {
        JwtLoginResponse loginResponse = jwtAuthService.login(request);
        return ResponseEntity.ok().body(SuccessResponseBody.builder()
                                                .status(HttpStatus.OK.value())
                                                .message("로그인에 성공하였습니다.")
                                                .data(loginResponse)
                                                           .build());
    }
    
    @PostMapping("/api/v1/test")
    public  ResponseEntity<String> test(@RequestBody String test) {
        return ResponseEntity.ok().body(test + " Received");
    }
}
