package com.app.UseGPSDeviceKafka;


//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;


//@SpringBootApplication
//@EnableKafka
//public class BusLocationUpdaterApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(BusLocationUpdaterApplication.class, args);
//    }

//    @Bean
//    public GPSDeviceListener gpsDeviceListener(KafkaTemplate<String, String> kafkaTemplate) {
//        return new GPSDeviceListener(kafkaTemplate);
//    }
//
//    create a Controller
//    @RestController
//    public static class LocationUpdateController {
//
//        private final GPSDeviceListener gpsDeviceListener;
//
//        public LocationUpdateController(GPSDeviceListener gpsDeviceListener) {
//            this.gpsDeviceListener = gpsDeviceListener;
//        }
//
//        @PostMapping("/gps-data")
//        public void receiveGPSData(@RequestBody String rawData) {
//            gpsDeviceListener.receiveGPSData(rawData);
//        }
//    }
//}
