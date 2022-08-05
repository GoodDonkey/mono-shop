package com.looselycoupled.monoshop.users;

import com.looselycoupled.monoshop.users.data.Role;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupRequest {
    
    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
    
    @Setter
    private Role role = Role.ROLE_MEMBER;
}
