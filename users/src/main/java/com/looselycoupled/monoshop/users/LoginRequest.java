package com.looselycoupled.monoshop.users;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
