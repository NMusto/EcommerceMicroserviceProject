package com.product_service.kafka.producer;

import com.product_service.kafka.event.StockEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, StockEvent> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, StockEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStockEvent(StockEvent stockEvent) {

        try {
            kafkaTemplate.send("stock-topic", stockEvent);
            log.info("Stock event sent: {}", stockEvent);
        } catch (Exception ex) {
            log.error("Fail to send stock event: {}", stockEvent);
        }
    }
}
