package com.looselycoupled.monoshop.application.sales.data;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrderItem {
    @Id @Getter
    @Type(type = "uuid-char")
    private UUID orderItemId;
}
