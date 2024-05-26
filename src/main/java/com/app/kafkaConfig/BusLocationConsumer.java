package com.app.kafkaConfig;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BusLocationConsumer {

    @KafkaListener(topics = "${bus.location.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeBusLocation(String busId, String newLocation) {
        // Update bus location in the application database or cache
        System.out.println("Received bus location update: Bus ID - " + busId + ", New Location - " + newLocation);
    }
}

