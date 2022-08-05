package com.looselycoupled.monoshop.warehouse;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class RegisterProduct {
    private UUID productId;
    private String productName;
}
