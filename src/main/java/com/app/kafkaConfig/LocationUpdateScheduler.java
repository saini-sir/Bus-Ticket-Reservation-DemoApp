package com.app.kafkaConfig;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LocationUpdateScheduler {

    private final GPSTrackingService gpsTrackingService;

    public LocationUpdateScheduler(GPSTrackingService gpsTrackingService) {
        this.gpsTrackingService = gpsTrackingService;
    }

    @Scheduled(fixedDelay = 60000) // Fetch updates every minute
    public void fetchLocationUpdates() {
        gpsTrackingService.fetchBusLocationUpdates();
    }
}

