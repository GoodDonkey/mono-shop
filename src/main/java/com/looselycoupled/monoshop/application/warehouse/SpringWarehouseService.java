package com.looselycoupled.monoshop.application.warehouse;

import com.looselycoupled.monoshop.application.warehouse.data.Product;
import com.looselycoupled.monoshop.application.warehouse.data.ProductRepository;
import com.looselycoupled.monoshop.common.events.ProductRegistered;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpringWarehouseService implements WarehouseService{
    
    private static final String PRODUCT_TOPIC = "product";
    private final ProductRepository productRepository;
    private final KafkaTemplate<String, ProductRegistered> kafkaTemplate;
    
    @Override
    @Transactional
    public void handle(RegisterProduct command) {
        log.debug("registerProduct command: {}", command);
        Product product = Product.builder()
                                  .productId(command.getProductId())
                                 .productName(command.getProductName()).build();
        Product savedProduct = productRepository.save(product);
        ProductRegistered event = ProductRegistered.builder().productId(savedProduct.getProductId()).build();
        kafkaTemplate.send(PRODUCT_TOPIC, event);
    }
}
