package com.app.UseGPSDeviceKafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BusLocationsConsumer {

    @KafkaListener(topics = "bus-locations", groupId = "bus-location-consumer-group")
    public void consumeBusLocation(String message) {
        // Process location update message received from Kafka
        String[] parts = message.split(",");
        String busId = parts[0];
        String latitude = parts[1];
        String longitude = parts[2];

        // Update bus location in the application database or cache
        System.out.println("Received bus location update: Bus ID - " + busId + ", Latitude - " + latitude + ", Longitude - " + longitude);
    }
}
