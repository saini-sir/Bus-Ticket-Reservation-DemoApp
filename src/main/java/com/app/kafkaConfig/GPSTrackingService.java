package com.app.kafkaConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GPSTrackingService {

    @Value("${gps.api.base-url}")
    private String baseUrl;

    @Value("${gps.api.token}")
    private String apiToken;

    private final RestTemplate restTemplate;

    public GPSTrackingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void fetchBusLocationUpdates() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiToken);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                baseUrl + "/locations",
                HttpMethod.GET,
                requestEntity,
                String.class
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String locationData = responseEntity.getBody();
            // Publish location updates to Kafka
            // You will implement this part next
        } else {
            System.err.println("Failed to fetch bus location updates. Status code: " + responseEntity.getStatusCode());
        }
    }
}

