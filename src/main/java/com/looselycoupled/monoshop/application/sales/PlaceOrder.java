package com.looselycoupled.monoshop.application.sales;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PlaceOrder {
    private int userId;
    private int productId;
    private int quantity;
}
