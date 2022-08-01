package com.looselycoupled.monoshop.application.users.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateRefreshToken {
    private String username;
}
