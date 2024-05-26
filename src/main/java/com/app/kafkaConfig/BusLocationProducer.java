package com.app.kafkaConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

//@Component
public class BusLocationProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${bus.location.topic}")
    private String topicName;

    public BusLocationProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendBusLocationUpdate(String busId, String newLocation) {
        kafkaTemplate.send(topicName, busId, newLocation);
    }
}

