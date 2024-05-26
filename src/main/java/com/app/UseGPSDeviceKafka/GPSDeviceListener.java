package com.app.UseGPSDeviceKafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class GPSDeviceListener {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName = "bus-locations";

    public GPSDeviceListener(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Method to receive GPS data from devices
    public void receiveGPSData(String rawData) {
        // Dummy implementation to extract bus ID, latitude, and longitude from raw GPS data
        String busId = "bus123";
        String latitude = "51.5074";
        String longitude = "0.1278";

        // Construct message for Kafka topic
        String locationUpdate = busId + "," + latitude + "," + longitude;

        // Publish location update to Kafka topic
        kafkaTemplate.send(topicName, busId, locationUpdate);
    }
}
