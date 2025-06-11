package com.product_service.kafka.producer;

import com.product_service.kafka.event.StockEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private final KafkaTemplate<String, StockEvent> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, StockEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStockEvent(StockEvent stockEvent) {
        kafkaTemplate.send("stock-topic", stockEvent);
    }
}
