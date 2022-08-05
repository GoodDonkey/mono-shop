package com.looselycoupled.monoshop.warehouse.data;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Product {
    @Id
    @Type(type = "uuid-char")
    private UUID productId;
    private String productName;
}
