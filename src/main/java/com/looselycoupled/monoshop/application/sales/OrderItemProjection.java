package com.looselycoupled.monoshop.application.sales;

import com.looselycoupled.monoshop.application.sales.data.OrderItem;
import com.looselycoupled.monoshop.application.sales.data.OrderItemRepository;
import com.looselycoupled.monoshop.common.events.ProductRegistered;
import com.looselycoupled.monoshop.common.kafka.Topics;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderItemProjection {
    
    private final OrderItemRepository orderItemRepository;
    @Getter
    private final CountDownLatch latch = new CountDownLatch(1);
    
    @KafkaListener(topics = Topics.PRODUCT, groupId = "sales")
    @Transactional
    public void on(ProductRegistered event) {
        log.debug("consuming event: {}", event);
        OrderItem orderItem = OrderItem.builder().orderItemId(event.getProductId()).build();
        orderItemRepository.save(orderItem);
        latch.countDown();
    }
}
