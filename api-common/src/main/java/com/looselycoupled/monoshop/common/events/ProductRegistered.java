package com.looselycoupled.monoshop.common.events;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductRegistered {
    private UUID productId;
}
