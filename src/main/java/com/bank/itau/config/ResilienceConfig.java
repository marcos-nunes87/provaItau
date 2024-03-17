package com.bank.itau.config;
//package com.bank.itau.circuitbreaker;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.client.RestTemplate;
//
//@Configuration
//public class ResilienceConfig {
//	@Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//
//    @Bean
//    public CircuitBreakerConfig circuitBreakerConfig() {
//        return CircuitBreakerConfig.custom()
//                .failureRateThreshold(50)
//                .waitDurationInOpenState(Duration.ofMillis(1000))
//                .permittedNumberOfCallsInHalfOpenState(2)
//                .slidingWindowSize(4)
//                .build();
//    }
//
//    @Bean
//    public TimeLimiterConfig timeLimiterConfig() {
//        return TimeLimiterConfig.custom()
//                .timeoutDuration(Duration.ofMillis(500))
//                .build();
//    }
//}
