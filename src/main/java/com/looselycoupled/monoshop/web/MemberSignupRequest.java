package com.looselycoupled.monoshop.web;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSignupRequest {
    
    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
    
    @Setter
    private Role role = Role.ROLE_MEMBER;
}
