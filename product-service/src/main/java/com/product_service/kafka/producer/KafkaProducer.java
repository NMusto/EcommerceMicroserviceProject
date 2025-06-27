package com.product_service.kafka.producer;

import com.product_service.kafka.event.ProductDeletedEvent;
import com.product_service.kafka.event.StockEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, StockEvent> stockEventKafkaTemplate;
    private final KafkaTemplate<String, ProductDeletedEvent> productDeletedKafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, StockEvent> stockEventKafkaTemplate,
                         KafkaTemplate<String, ProductDeletedEvent> productDeletedKafkaTemplate) {
        this.stockEventKafkaTemplate = stockEventKafkaTemplate;
        this.productDeletedKafkaTemplate = productDeletedKafkaTemplate;
    }

    public void sendStockEvent(StockEvent stockEvent) {

        try {
            stockEventKafkaTemplate.send("stock-topic", stockEvent);
            log.info("Stock event sent: {}", stockEvent);
        } catch (Exception ex) {
            log.error("Fail to send stock event: {}", stockEvent);
        }
    }

    public void sendProductDeleteEvent(ProductDeletedEvent event) {

        try {
            productDeletedKafkaTemplate.send("product-delete-topic", event);
            log.info("Product deleted event sent: {}", event);
        } catch (Exception ex) {
            log.error("Fail to send product deleted event: {}", event);
        }
    }
}
