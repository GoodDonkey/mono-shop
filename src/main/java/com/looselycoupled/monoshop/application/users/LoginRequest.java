package com.looselycoupled.monoshop.application.users;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
