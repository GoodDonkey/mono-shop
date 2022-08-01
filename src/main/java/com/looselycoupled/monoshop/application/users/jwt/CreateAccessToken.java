package com.looselycoupled.monoshop.application.users.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateAccessToken {
    private String username;
}
