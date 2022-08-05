package com.looselycoupled.monoshop.integration;

import com.looselycoupled.monoshop.application.sales.OrderItemProjection;
import com.looselycoupled.monoshop.application.sales.data.OrderItemRepository;
import com.looselycoupled.monoshop.application.warehouse.RegisterProduct;
import com.looselycoupled.monoshop.application.warehouse.WarehouseService;
import com.looselycoupled.monoshop.application.warehouse.data.Product;
import com.looselycoupled.monoshop.application.warehouse.data.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9093", "port=9093" })
public class KafkaIntegrationTest {
    
    @Autowired
    WarehouseService warehouseService;
    
    @Autowired
    OrderItemProjection orderItemProjection;
    
    @Autowired
    OrderItemRepository orderItemRepository;
    
    @Autowired
    ProductRepository productRepository;
    
    private static final String PRODUCT_TOPIC = "product";
    
    @Test
    @DisplayName("warehouse 에서 Product 가 저장되면, " +
                 "Sales 에서도 OrderItem 이 저장된다.")
    void test01() throws InterruptedException {
        // given
        UUID productId = UUID.randomUUID();
        RegisterProduct command = RegisterProduct.builder()
                                                 .productId(productId)
                                                 .productName("kafkaTestingProduct01").build();
        // when
        warehouseService.handle(command);
        
        // then
        boolean isConsumed = orderItemProjection.getLatch().await(3, TimeUnit.SECONDS);
        assertThat(isConsumed).isTrue();
        Product product = productRepository.findById(productId).orElse(null);
        assertThat(product).isNotNull();
    
        assertThatNoException().isThrownBy(() -> orderItemRepository.findById(productId).orElseThrow());
    }
}
